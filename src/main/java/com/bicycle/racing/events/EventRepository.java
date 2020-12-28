package com.bicycle.racing.events;

import com.bicycle.racing.events.model.Event;

import java.util.List;

public interface EventRepository {
    List<Event> findAll();
}
