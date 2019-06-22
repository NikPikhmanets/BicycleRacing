package com.bicycleracing.service;

import com.bicycleracing.model.Event;
import com.bicycleracing.model.EventResult;
import com.bicycleracing.model.EventResultPage;
import com.bicycleracing.repository.EventResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventResultService {

    private final EventResultRepository eventResultRepository;
    private final EventService eventService;

    @Autowired
    public EventResultService(EventResultRepository eventResultRepository,
                              EventService eventService) {
        this.eventResultRepository = eventResultRepository;
        this.eventService = eventService;
    }

    public void saveUserResult(EventResult eventResult) {
        EventResult eventResultByUsernameAndEventId = eventResultRepository.getResultByUsernameAndEventId(eventResult);

        if (eventResultByUsernameAndEventId != null) {
            eventResultRepository.updateResult(eventResult);

            return;
        }
        eventResultRepository.saveResult(eventResult);
    }

    public EventResultPage getListResultByEventId(Integer eventId) {
        Event event = eventService.getEventById(eventId);
        List<EventResult> eventResults = eventResultRepository.getResultByEventId(eventId);

        EventResultPage eventResultPage = new EventResultPage();
        eventResultPage.setEvent(event);
        eventResultPage.setEventResults(eventResults);

        return eventResultPage;
    }
}
