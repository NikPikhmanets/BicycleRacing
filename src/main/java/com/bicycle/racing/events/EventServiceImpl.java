package com.bicycle.racing.events;

import com.bicycle.racing.events.data.DemoEventsDataBuilder;
import com.bicycle.racing.events.data.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class EventServiceImpl implements EventService<Event> {

    private final EventRepository<Event> repository;

    @Autowired
    public EventServiceImpl(EventRepository<Event> repository) {
        this.repository = repository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void addDemoData() {
        Optional<List<Event>> optionalEvents = repository.findAll();

        if (optionalEvents.get().isEmpty()) {
            List<Event> events = new DemoEventsDataBuilder().getEvents();
            saveList(events);
        } else {
            System.out.println("events = " + optionalEvents.get());
        }
    }

    @Override
    public List<Event> getAll() {
        Optional<List<Event>> optionalEvents = repository.findAll();
        return optionalEvents.orElse(Collections.emptyList());
    }

    @Override
    public Event getById(long id) {
        return null;
    }

    @Override
    public void save(Event event) {

    }

    @Override
    public void saveList(List<Event> events) {
        repository.insertList(events);
    }

    @Override
    public int getSampleTrackId(long id) {
        return 0;
    }
}
