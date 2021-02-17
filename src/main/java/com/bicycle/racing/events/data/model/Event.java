package com.bicycle.racing.events.data.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(columnDefinition = "text")
    private String type;

    @Column(columnDefinition = "text")
    private String title;

    @Column(columnDefinition = "int", name = "simple_track_id")
    private int sampleTrackId;

    @Column(columnDefinition = "TIMESTAMP", name = "time_start")
    private LocalDateTime timeStart;

    @Column(columnDefinition = "TIMESTAMP", name = "time_limit")
    private LocalTime timeLimit;

    @Column(columnDefinition = "int")
    private int distance;

    @Column(columnDefinition = "text")
    private String content;

    @Column(columnDefinition = "TIMESTAMP", name = "created_at")
    private LocalDateTime createdAt;

    @Column(columnDefinition = "TIMESTAMP", name = "edited_at")
    private LocalDateTime editedAt;
}
//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")