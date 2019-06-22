package com.bicycleracing.controller;

import com.bicycleracing.model.Event;
import com.bicycleracing.model.EventRegistration;
import com.bicycleracing.service.EventRegistrationService;
import com.bicycleracing.service.EventService;
import com.bicycleracing.service.PresenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
public class EventController {

    public final static int PAGE_SIZE = 5;

    private final EventService eventService;
    private final EventRegistrationService eventRegistrationService;
    private final PresenterService presenterService;

    @Autowired
    public EventController(EventService eventService,
                           EventRegistrationService eventRegistrationService,
                           PresenterService presenterService) {
        this.eventService = eventService;
        this.eventRegistrationService = eventRegistrationService;
        this.presenterService = presenterService;
    }

    @GetMapping("/view_event")
    public String viewEvent(@RequestParam(value = "eventId", required = false) Integer eventId,
                            Model model) {
        Event event = eventService.getEventById(eventId);
        model.addAttribute("event", event);

        return "events/view_event";
    }

    @GetMapping(value = "/join")
    public String join(@RequestParam(value = "eventId") Integer eventId,
                       Principal principal) {
        EventRegistration eventRegistration = new EventRegistration();
        eventRegistration.setEventId(eventId);
        eventRegistration.setUsername(principal.getName());
        eventRegistrationService.addUserToEvent(eventRegistration);

        return "redirect:/profile";
    }

    @GetMapping(value = "/add_event")
    public String save(Model model, Principal principal) {
        retrieveAndSetUser(model, principal);
        model.addAttribute("event", new Event());

        return "events/add_event";
    }

    @PostMapping(value = "/add_event")
    public String save(@ModelAttribute Event event,
                       @RequestParam("file") MultipartFile trackFile,
                       Model model) {

        if (presenterService.saveEventAndSampleTrack(event, trackFile)) {
            model.addAttribute("message", "You successfully add event");
        } else {
            model.addAttribute("message", "Failed add event");
        }
        return "events/add_event";
    }

    @GetMapping(value = "/events/{page}", produces = "application/json")
    @ResponseBody
    public Page<Event> events(@PathVariable Integer page) {
        return eventService.getPages(PageRequest.of(page, PAGE_SIZE));
    }

    @GetMapping(value = {"/events", "/"})
    public String events(Model model, Principal principal) {
        retrieveAndSetUser(model, principal);

        return "events/events";
    }

    private void retrieveAndSetUser(Model model, Principal principal) {
        if (principal != null) {
            User user = (User) ((Authentication) principal).getPrincipal();
            model.addAttribute("user", "Welcome, " + user.getUsername());
        }
    }
}
