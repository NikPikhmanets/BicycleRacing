package com.bicycle.racing.analysis;


import com.bicycle.racing.analysis.utils.DistanceUtils;
import com.bicycle.racing.analysis.utils.LatLng;
import com.bicycle.racing.gpx.GPXParser;
import com.bicycle.racing.gpx.data.GPX;
import com.bicycle.racing.gpx.data.Track;
import com.bicycle.racing.gpx.data.Waypoint;
import com.bicycle.racing.events.model.Event;
import com.bicycle.racing.model.EventResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class AnalysisTrack {

    public static final int RADIUS = 5;
    public static final boolean COMPLETED = true;
    public static final boolean FAILED = false;
    public static final String BREVET = "Brevet";
    public static final int FIRST_WAYPOINT = 0;

    private final GPXParser gpxParser;
    private List<Waypoint> waypoints;

    @Autowired
    public AnalysisTrack(GPXParser gpxParser) {
        this.gpxParser = gpxParser;
    }

    public GPX parseGpxFile(InputStream inputStream) {
        try {
            return gpxParser.parseGPX(inputStream);
        } catch (Exception e) {
            System.err.println("Failed parse gpx file");
            return null;
        }
    }

    public EventResult analysisTrack(Event event, GPX gpx, List<Waypoint> simpleWaypoints) {
        waypoints = getTrackWaypoints(gpx);

        return analysisWithSimpleTrack(event, simpleWaypoints);
    }

    private EventResult analysisWithSimpleTrack(Event event, List<Waypoint> simpleWaypoints) {
        EventResult eventResult = new EventResult();
        LocalDateTime dateFirstWayPoint = null;
        LocalDateTime dateLastWayPoint = null;
        int numberSampleWaypoints = simpleWaypoints.size();
        int countSampleWaypoints = 0;
        int position = 0;

        for (int i = 0; i < numberSampleWaypoints; i++) {
            LatLng sampleLatLng = getLatLng(simpleWaypoints.get(i));

            for (int y = position; y < waypoints.size(); y++) {
                Waypoint waypoint = waypoints.get(y);

                LatLng latLng = getLatLng(waypoint);
                double distance = DistanceUtils.computeDistanceBetween(sampleLatLng, latLng);

                if (distance <= RADIUS) {
                    position = y;
                    countSampleWaypoints++;

                    if (i == FIRST_WAYPOINT) {
                        dateFirstWayPoint = waypoint.getTime();
                    }
                    if (i == numberSampleWaypoints - 1) {
                        dateLastWayPoint = waypoint.getTime();
                    }
                    break;
                }
            }

            if (numberSampleWaypoints == countSampleWaypoints) {
                break;
            }
        }

        if (countSampleWaypoints == numberSampleWaypoints) {
            LocalTime timeRide;

            if (event.getType().equals(BREVET)) {
                LocalDateTime timeStart = event.getTimeStart();
                LocalDateTime timeFinish = timeStart.plusMinutes(event.getTimeLimit().getMinute());
                timeRide = getTimeRide(timeStart, dateLastWayPoint);
                eventResult.setStatus(timeFinish.isAfter(Objects.requireNonNull(dateLastWayPoint)) ? FAILED : COMPLETED);
            } else {
                timeRide = getTimeRide(dateFirstWayPoint, dateLastWayPoint);
                eventResult.setStatus(COMPLETED);
            }
            eventResult.setTime(timeRide);

            return eventResult;
        }
        eventResult.setStatus(FAILED);

        return eventResult;
    }

    private LocalTime getTimeRide(LocalDateTime timeStart, LocalDateTime timeEnd) {
        long seconds = ChronoUnit.SECONDS.between(Objects.requireNonNull(timeStart), timeEnd);

        return LocalTime.ofSecondOfDay(seconds);
    }

    public List<Waypoint> getTrackWaypoints(GPX gpx) {
        List<Waypoint> waypoints = new ArrayList<>();

        for (Track track : gpx.getTracks()) {
            waypoints.addAll(track.getTrackPoints());
        }
        return waypoints;
    }

    private LatLng getLatLng(Waypoint waypoint) {
        return new LatLng(
                waypoint.getLatitude(),
                waypoint.getLongitude()
        );
    }
}
