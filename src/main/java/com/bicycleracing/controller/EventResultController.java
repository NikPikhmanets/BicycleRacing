package com.bicycleracing.controller;

import com.bicycleracing.model.EventResultPage;
import com.bicycleracing.service.EventResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EventResultController {

    private final EventResultService service;

    @Autowired
    public EventResultController(EventResultService service) {
        this.service = service;
    }

    @GetMapping(value = "/result")
    public String result(@RequestParam("eventId") Integer eventId,
                         Model model) {
        EventResultPage eventResultPage = service.getListResultByEventId(eventId);
        model.addAttribute("eventResultPage", eventResultPage);

        return "events/event_result";
    }
}
