package com.bicycle.racing.events;

import com.bicycle.racing.events.data.model.Event;

import java.util.List;

public interface EventService {
    List<Event> getAll();

    Event getById(long id);

    void save(Event t);

    void saveList(List<Event> list);

    int getSampleTrackId(long id);
}
