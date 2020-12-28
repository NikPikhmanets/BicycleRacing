package com.bicycle.racing.model.form;

import org.springframework.web.multipart.MultipartFile;

public class UserFileForm {

    private int eventId;
    private String username;
    private MultipartFile trackFile;

    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MultipartFile getTrackFile() {
        return trackFile;
    }

    public void setTrackFile(MultipartFile trackFile) {
        this.trackFile = trackFile;
    }
}
