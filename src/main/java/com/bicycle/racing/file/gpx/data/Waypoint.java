package com.bicycle.racing.file.gpx.data;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Waypoint {

    private Double ageOfGPSData;
    private String comment;
    private String description;
    private Integer dgpsid;
    private Double elevation;
    private Double geoidHeight;
    private Double hdop;
    private Double latitude;
    private Double longitude;
    private Double magneticDeclination;
    private String name;
    private Double pdop;
    private Integer sat;
    private String src;
    private String sym;
    private LocalDateTime time;
    private String type;
    private Double vdop;
}
