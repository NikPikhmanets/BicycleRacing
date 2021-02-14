package com.bicycle.racing.file.gpx.data;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Route {

    private String name;
    private String comment;
    private String description;
    private String src;
    private Integer number;
    private String type;
    private List<Waypoint> routePoints;

    public void addRoutePoint(Waypoint waypoint) {
        if (routePoints == null) {
            routePoints = new ArrayList<>();
        }
        routePoints.add(waypoint);
    }

    public String toString() {
        return String.format("rte[name:%s rtepts:%d]", name, routePoints != null ? routePoints.size() : 0);
    }
}
