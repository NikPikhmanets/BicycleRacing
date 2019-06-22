package com.bicycleracing.service;

import com.bicycleracing.analysis.AnalysisTrack;
import com.bicycleracing.gpx.data.GPX;
import com.bicycleracing.gpx.data.Waypoint;
import com.bicycleracing.model.Event;
import com.bicycleracing.model.EventResult;
import com.bicycleracing.model.form.UserFileForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Service
public class PresenterService {

    private final TrackService trackService;
    private final EventService eventService;
    private final AnalysisTrack analysisTrack;
    private final EventResultService eventResultService;

    @Autowired
    public PresenterService(TrackService trackService,
                            EventService eventService,
                            AnalysisTrack analysisTrack,
                            EventResultService eventResultService) {

        this.trackService = trackService;
        this.eventService = eventService;
        this.analysisTrack = analysisTrack;
        this.eventResultService = eventResultService;
    }

    public boolean saveUserTrackByEventId(UserFileForm userFileForm) {
        GPX gpx = getGpx(userFileForm.getTrackFile());

        if (gpx != null) {
            List<Waypoint> trackWaypoints = analysisTrack.getTrackWaypoints(gpx);
            trackService.saveTrackAndWaypoints(userFileForm, trackWaypoints);

            List<Waypoint> simpleWaypoints = trackService.getSimpleWaypointsByTrackId(userFileForm.getEventId());
            Event event = eventService.getEventById(userFileForm.getEventId());

            EventResult eventResult = analysisTrack.analysisTrack(event, gpx, simpleWaypoints);
            eventResult.setEventId(userFileForm.getEventId());
            eventResult.setUsername(userFileForm.getUsername());
            eventResultService.saveUserResult(eventResult);

            return true;
        }
        return false;
    }

    public boolean saveEventAndSampleTrack(Event event, MultipartFile trackFile) {
        GPX gpx = getGpx(trackFile);

        if (gpx != null) {
            int trackId = trackService.saveSampleTrackAndWaypoint(trackFile.getOriginalFilename(), gpx);
            event.setSampleTrackId(trackId);
            eventService.saveEvent(event);

            return true;
        }
        return false;
    }

    private GPX getGpx(MultipartFile trackFile) {
        try (InputStream inputStream = trackFile.getInputStream()) {

            return analysisTrack.parseGpxFile(inputStream);
        } catch (IOException e) {
            System.err.println("Failed get stream");

            return null;
        }
    }
}
