package com.bicycleracing.gpx.data;

import java.util.ArrayList;
import java.util.List;

public class Track {

    private String name;
    private String comment;
    private String description;
    private String src;
    private Integer number;
    private String type;
    private List<Waypoint> trackPoints;
    private List<List<Waypoint>> trackSegments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Waypoint> getTrackPoints() {
        return trackPoints;
    }

    public void setTrackPoints(List<Waypoint> trackPoints) {
        this.trackPoints = trackPoints;
    }

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
        StringBuilder sb = new StringBuilder();
        sb.append("trk[");
        sb.append("name:").append(name).append(" ");
        int points = 0;

        if (trackPoints != null) {
            points = trackPoints.size();
        }
        sb.append("trkseg:").append(points).append(" ");
        sb.append("]");
        return sb.toString();
    }
}
