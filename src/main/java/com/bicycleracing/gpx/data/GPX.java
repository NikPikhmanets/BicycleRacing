package com.bicycleracing.gpx.data;

import java.util.ArrayList;
import java.util.List;

public class GPX {

    private String creator;
    private List<Route> routes;
    private List<Track> tracks;
    private String version;
    private List<Waypoint> waypoints;

    public GPX() {
        this.waypoints = new ArrayList<>();
        this.tracks = new ArrayList<>();
        this.routes = new ArrayList<>();
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

    public String getCreator() {
        return this.creator;
    }

    public List<Route> getRoutes() {
        return this.routes;
    }

    public List<Track> getTracks() {
        return this.tracks;
    }

    public String getVersion() {
        return this.version;
    }

    public List<Waypoint> getWaypoints() {
        return this.waypoints;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public void setTracks(List<Track> tracks) {
        this.tracks = tracks;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setWaypoints(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }
}
