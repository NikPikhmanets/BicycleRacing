package com.bicycleracing.service;

import com.bicycleracing.model.Event;
import com.bicycleracing.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class EventService {

    private final EventRepository repository;

    @Autowired
    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public void saveEvent(Event event) {
        repository.save(event);
    }

    public Page<Event> getPages(Pageable pageable) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber() - 1;
        int startItem = currentPage * pageSize;
        int totalCount = repository.getTotalCount();
        List<Event> list;

        if (totalCount < startItem) {
            list = Collections.emptyList();
        } else {
            list = repository.getEvents(startItem, pageSize);
        }
        return new PageImpl<>(list, PageRequest.of(currentPage, pageSize), totalCount);
    }

    public int getSampleTrackId(int eventId) {
        return repository.getSampleTrackId(eventId);
    }

    public Event getEventById(int eventId) {
        return repository.getEventById(eventId);
    }
}
