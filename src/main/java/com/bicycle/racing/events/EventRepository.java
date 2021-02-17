package com.bicycle.racing.events;


import com.bicycle.racing.events.data.model.Event;

import java.util.List;

public interface EventRepository {
    List<Event> findAll();

    void saveAll(List<Event> list);
}
