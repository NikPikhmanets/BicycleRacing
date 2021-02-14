package com.bicycle.racing.file.gpx.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Track {

    private String name;
    private String comment;
    private String description;
    private String src;
    private Integer number;
    private String type;
    private List<Waypoint> trackPoints;
    private List<List<Waypoint>> trackSegments;

    public void addTrackPoint(Waypoint trackPoint) {
        if (trackPoints == null) {
            trackPoints = new ArrayList<>();
        }
        trackPoints.add(trackPoint);
    }

    public void addTrackPoints(List<Waypoint> trackPoints) {
        if (this.trackPoints == null) {
            this.trackPoints = new ArrayList<>();
        }
        this.trackPoints.addAll(trackPoints);
    }

    public void addTrackSegment(List<Waypoint> segment) {
        if (this.trackSegments == null) {
            this.trackSegments = new ArrayList<>();
        }
        this.trackSegments.add(segment);
    }

    public List<List<Waypoint>> getTrackSegments() {
        return this.trackSegments;
    }

    public String toString() {
        return String.format("trk[name:%s trkseg:%d]", name, trackPoints != null ? trackPoints.size() : 0);
    }
}
