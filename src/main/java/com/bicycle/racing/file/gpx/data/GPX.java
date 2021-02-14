package com.bicycle.racing.file.gpx.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GPX {

    private String creator;
    private List<Route> routes;
    private List<Track> tracks;
    private String version;
    private List<Waypoint> waypoints;

    public GPX() {
    }

    public void addRoute(Route route) {
        if (this.routes == null) {
            this.routes = new ArrayList<>();
        }
        this.routes.add(route);
    }

    public void addTrack(Track track) {
        if (this.tracks == null) {
            this.tracks = new ArrayList<>();
        }
        this.tracks.add(track);
    }

    public void addWaypoint(Waypoint waypoint) {
        if (this.waypoints == null) {
            this.waypoints = new ArrayList<>();
        }
        this.waypoints.add(waypoint);
    }
}
