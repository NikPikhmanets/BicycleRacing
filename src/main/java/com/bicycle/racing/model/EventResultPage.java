package com.bicycle.racing.model;

import com.bicycle.racing.events.data.model.Event;

import java.util.List;

public class EventResultPage {

    private int id;
    private Event event;
    private List<EventResult> eventResults;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public List<EventResult> getEventResults() {
        return eventResults;
    }

    public void setEventResults(List<EventResult> eventResults) {
        this.eventResults = eventResults;
    }
}
