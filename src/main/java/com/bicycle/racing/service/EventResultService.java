package com.bicycle.racing.service;

import com.bicycle.racing.events.EventServiceImpl;
import com.bicycle.racing.events.data.model.Event;
import com.bicycle.racing.model.EventResult;
import com.bicycle.racing.model.EventResultPage;
import com.bicycle.racing.repository.EventResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventResultService {

    private final EventResultRepository eventResultRepository;
    private final EventServiceImpl eventService;

    @Autowired
    public EventResultService(EventResultRepository eventResultRepository,
                              EventServiceImpl eventService) {
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
        Event event = eventService.getById(eventId);
        List<EventResult> eventResults = eventResultRepository.getResultByEventId(eventId);

        EventResultPage eventResultPage = new EventResultPage();
        eventResultPage.setEvent(event);
        eventResultPage.setEventResults(eventResults);

        return eventResultPage;
    }
}
