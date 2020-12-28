package com.bicycle.racing.events;

import com.bicycle.racing.events.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository repository;

    @Autowired
    public EventServiceImpl(EventRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Event> getEvents() {
        return repository.findAll();
    }

    @Override
    public Event getEventById(Integer eventId) {
        return null;
    }

    @Override
    public void saveEvent(Event event) {

    }

    @Override
    public int getSampleTrackId(int eventId) {
        return 0;
    }
}
