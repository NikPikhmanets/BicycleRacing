package com.bicycle.racing.events.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Getter
@Setter
@Builder
public class Event {

    private int id;
    private String type;
    private String title;
    private int sampleTrackId;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime timeStart;
    private LocalTime timeLimit;
    private int distance;
    private String content;
}
