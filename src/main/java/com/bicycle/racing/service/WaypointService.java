package com.bicycle.racing.service;

import com.bicycle.racing.file.gpx.data.Waypoint;
import com.bicycle.racing.model.BatchWaypoint;
import com.bicycle.racing.repository.WaypointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WaypointService {

    private final WaypointRepository waypointRepository;

    @Autowired
    public WaypointService(WaypointRepository waypointRepository) {
        this.waypointRepository = waypointRepository;
    }

    public List<Waypoint> getSimpleWaypointsByTrackId(int sampleTrackId) {
        return waypointRepository.getSimpleWaypointsByTrackId(sampleTrackId);
    }

    public void saveWaypoints(List<Waypoint> waypoints, int trackId) {
        waypointRepository.saveWayPoints(getBathWaypoints(waypoints, trackId));
    }

    public void updateWaypoints(List<Waypoint> trackWaypoints, int trackId) {
        waypointRepository.deleteWaypoints(trackId);
        waypointRepository.saveWayPoints(getBathWaypoints(trackWaypoints, trackId));
    }

    public void saveSampleWaypoints(List<Waypoint> waypoints, int trackId) {
        waypointRepository.saveSampleWaypoints(getBathWaypoints(waypoints, trackId));
    }

    private List<BatchWaypoint> getBathWaypoints(List<Waypoint> waypoints, int trackId) {
        return waypoints.stream().map(waypoint -> new BatchWaypoint(
                waypoint.getLatitude(),
                waypoint.getLongitude(),
                waypoint.getTime(),
                trackId
        )).collect(Collectors.toList());
    }
}
