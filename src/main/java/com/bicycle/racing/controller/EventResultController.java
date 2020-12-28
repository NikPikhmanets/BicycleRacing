package com.bicycle.racing.controller;

import com.bicycle.racing.service.EventResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/results")
public class EventResultController {

    private final EventResultService service;

    @Autowired
    public EventResultController(EventResultService service) {
        this.service = service;
    }

//    @GetMapping(value = "/result")
//    public String result(@RequestParam("eventId") Integer eventId,
//                         Model model) {
//        EventResultPage eventResultPage = service.getListResultByEventId(eventId);
//        model.addAttribute("eventResultPage", eventResultPage);
//
//        return "events/event_result";
//    }
}
