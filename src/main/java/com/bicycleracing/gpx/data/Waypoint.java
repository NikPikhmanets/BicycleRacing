package com.bicycleracing.gpx.data;

import java.time.LocalDateTime;

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

    public Double getAgeOfGPSData() {
        return ageOfGPSData;
    }

    public void setAgeOfGPSData(Double ageOfGPSData) {
        this.ageOfGPSData = ageOfGPSData;
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

    public Integer getDgpsid() {
        return dgpsid;
    }

    public void setDgpsid(Integer dgpsid) {
        this.dgpsid = dgpsid;
    }

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public Double getGeoidHeight() {
        return geoidHeight;
    }

    public void setGeoidHeight(Double geoidHeight) {
        this.geoidHeight = geoidHeight;
    }

    public Double getHdop() {
        return hdop;
    }

    public void setHdop(Double hdop) {
        this.hdop = hdop;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getMagneticDeclination() {
        return magneticDeclination;
    }

    public void setMagneticDeclination(Double magneticDeclination) {
        this.magneticDeclination = magneticDeclination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPdop() {
        return pdop;
    }

    public void setPdop(Double pdop) {
        this.pdop = pdop;
    }

    public Integer getSat() {
        return sat;
    }

    public void setSat(Integer sat) {
        this.sat = sat;
    }

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }

    public String getSym() {
        return sym;
    }

    public void setSym(String sym) {
        this.sym = sym;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getVdop() {
        return vdop;
    }

    public void setVdop(Double vdop) {
        this.vdop = vdop;
    }
}
