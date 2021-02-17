package com.bicycle.racing.events;

import com.bicycle.racing.events.data.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventServiceImpl implements EventService<Event> {

    private final EventJpaRepository repository;

    @Autowired
    public EventServiceImpl(EventJpaRepository repository) {
        this.repository = repository;
    }

//    @EventListener(ApplicationReadyEvent.class)
//    public void addDemoData() {
//        Optional<List<Event>> optionalEvents = repository.findAll();
//
//        if (optionalEvents.get().isEmpty()) {
//            List<Event> events = new DemoEventsDataBuilder().getEvents();
//            saveList(events);
//        } else {
//            System.out.println("events = " + optionalEvents.get());
//        }
//    }

    @Override
    public List<Event> getAll() {
//        Optional<List<Event>> optionalEvents = repository.findAll();
//        return optionalEvents.orElse(Collections.emptyList());
        return repository.findAll();
    }

    @Override
    public Event getById(long id) {
        return null;
    }

    @Override
    public void save(Event event) {
        repository.save(event);
    }

    @Override
    public void saveList(List<Event> events) {
        repository.saveAll(events);
    }

    @Override
    public int getSampleTrackId(long id) {
        return 0;
    }
}
