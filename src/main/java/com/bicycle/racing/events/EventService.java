package com.bicycle.racing.events;

import com.bicycle.racing.events.model.Event;

import java.util.List;

public interface EventService {
    List<Event> getEvents();

    Event getEventById(Integer eventId);

    void saveEvent(Event event);

    int getSampleTrackId(int eventId);
}
