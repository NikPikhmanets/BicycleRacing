package com.bicycle.racing.service;

import com.bicycle.racing.events.EventServiceImpl;
import com.bicycle.racing.file.gpx.data.GPX;
import com.bicycle.racing.file.gpx.data.Track;
import com.bicycle.racing.file.gpx.data.Waypoint;
import com.bicycle.racing.repository.TrackRepository;
import com.bicycle.racing.model.form.UserFileForm;
import com.bicycle.racing.model.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TrackService {

    private static final int ABSENT_TRACK = -1;

    private final TrackRepository trackRepository;
    private final EventServiceImpl eventService;
    private final WaypointService waypointService;
    private final EventRegistrationService eventRegistrationService;

    @Autowired
    public TrackService(TrackRepository trackRepository,
                        EventServiceImpl eventService,
                        WaypointService waypointService,
                        EventRegistrationService eventRegistrationService) {
        this.trackRepository = trackRepository;
        this.eventService = eventService;
        this.waypointService = waypointService;
        this.eventRegistrationService = eventRegistrationService;
    }

    public List<Waypoint> getSimpleWaypointsByTrackId(int eventId) {
        int sampleTrackId = eventService.getSampleTrackId(eventId);

        return waypointService.getSimpleWaypointsByTrackId(sampleTrackId);
    }

    public void saveTrackAndWaypoints(UserFileForm userFileForm, List<Waypoint> trackWaypoints) {
        int trackId = eventRegistrationService.getTrackIdByEventId(
                userFileForm.getEventId(),
                userFileForm.getUsername()
        );

        if (trackId == ABSENT_TRACK) {
            trackId = trackRepository.saveNameTrack(userFileForm.getTrackFile().getOriginalFilename());
            waypointService.saveWaypoints(trackWaypoints, trackId);

            UserForm userForm = getUserForm(userFileForm, trackId);
            eventRegistrationService.saveTrackId(userForm);
        } else {
            trackRepository.updateNameTrack(trackId, userFileForm.getTrackFile().getOriginalFilename());
            waypointService.updateWaypoints(trackWaypoints, trackId);
        }
    }

    public int saveSampleTrackAndWaypoint(String nameTrack, GPX gpx) {
        int trackId = trackRepository.saveNameSampleTrack(nameTrack);
        List<Waypoint> waypoints = getTrackWaypoints(gpx);
        waypointService.saveSampleWaypoints(waypoints, trackId);

        return trackId;
    }

    public List<Waypoint> getTrackWaypoints(GPX gpx) {
        List<Waypoint> waypoints = new ArrayList<>();

        for (Track track : gpx.getTracks()) {
            waypoints.addAll(track.getTrackPoints());
        }
        return waypoints;
    }

    private UserForm getUserForm(UserFileForm userFileForm, int trackId) {
        UserForm userForm = new UserForm();
        userForm.setEventId(userFileForm.getEventId());
        userForm.setTrackId(trackId);
        userForm.setUsername(userFileForm.getUsername());

        return userForm;
    }
}
