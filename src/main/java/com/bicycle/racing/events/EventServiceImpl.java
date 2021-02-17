package com.bicycle.racing.events;

import com.bicycle.racing.events.data.model.Event;
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
        return repository.findAll();
    }

    @Override
    public Event getById(long id) {
        return null;
    }

    @Override
    public void save(Event event) {
//        repository.save(event);
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
