package com.bicycle.racing.gpx.data;

import java.util.ArrayList;
import java.util.List;

public class Route {

    private String name;
    private String comment;
    private String description;
    private String src;
    private Integer number;
    private String type;
    private List<Waypoint> routePoints;

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

    public List<Waypoint> getRoutePoints() {
        return routePoints;
    }

    public void setRoutePoints(ArrayList<Waypoint> routePoints) {
        this.routePoints = routePoints;
    }

    public void addRoutePoint(Waypoint waypoint) {
        if (routePoints == null) {
            routePoints = new ArrayList<>();
        }
        routePoints.add(waypoint);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("rte[");
        sb.append("name:").append(name).append(" ");
        int points = 0;

        if (routePoints != null) {
            points = routePoints.size();
        }
        sb.append("rtepts:").append(points).append(" ");
        sb.append("]");

        return sb.toString();
    }
}
