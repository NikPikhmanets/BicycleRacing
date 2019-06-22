package com.bicycleracing.model;

import java.time.LocalDateTime;

public class UserEvent {

    private int id;
    private int eventId;
    private String nameEvent;
    private String typeEvent;
    private int distance;
    private LocalDateTime timeStart;
    private String trackName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public String getTypeEvent() {
        return typeEvent;
    }

    public void setTypeEvent(String typeEvent) {
        this.typeEvent = typeEvent;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public LocalDateTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalDateTime timeStart) {
        this.timeStart = timeStart;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    @Override
    public String toString() {
        return "UserEvent{" +
                "eventId=" + eventId +
                ", nameEvent='" + nameEvent + '\'' +
                ", distance=" + distance +
                ", timeStart=" + timeStart +
                ", trackName='" + trackName + '\'' +
                '}';
    }
}
