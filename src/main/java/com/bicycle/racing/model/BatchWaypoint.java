package com.bicycle.racing.model;

import java.time.LocalDateTime;

public class BatchWaypoint {

    private double latitude;
    private double longitude;
    private LocalDateTime time;
    private int track_id;

    public BatchWaypoint(double latitude, double longitude, LocalDateTime time, int track_id) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.time = time;
        this.track_id = track_id;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LocalDateTime getDate() {
        return time;
    }

    public void setDate(LocalDateTime time) {
        this.time = time;
    }

    public int getTrack_id() {
        return track_id;
    }

    public void setTrack_id(int track_id) {
        this.track_id = track_id;
    }
}
