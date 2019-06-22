package com.bicycleracing.controller;

import com.bicycleracing.model.UserEvent;
import com.bicycleracing.service.EventRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

import static com.bicycleracing.controller.EventController.PAGE_SIZE;

@Controller
public class UserController {

    private final EventRegistrationService eventRegistrationService;

    @Autowired
    public UserController(EventRegistrationService eventRegistrationService) {
        this.eventRegistrationService = eventRegistrationService;
    }

    @GetMapping("/profile")
    public String profile(Model model, Principal principal) {
        retrieveAndSetUser(model, principal);

        return "user/profile";
    }

    @GetMapping(value = "/profile/{page}", produces = "application/json")
    @ResponseBody
    public Page<UserEvent> events(@PathVariable Integer page, Principal principal) {
        String username = principal.getName();
        return eventRegistrationService.getUserEventsPage(PageRequest.of(page, PAGE_SIZE), username);
    }

    @GetMapping(value = "/403")
    public String accessDenied(Model model, Principal principal) {

        if (principal != null) {
            User user = (User) ((Authentication) principal).getPrincipal();
            model.addAttribute("user", user.getUsername());
            String message = "Hi " + principal.getName()
                    + "! You do not have permission to access this page!";
            model.addAttribute("message", message);
        }
        return "user/403Page";
    }

    private void retrieveAndSetUser(Model model, Principal principal) {
        if (principal != null) {
            User user = (User) ((Authentication) principal).getPrincipal();
            model.addAttribute("user", "Welcome, " + user.getUsername());
            model.addAttribute("eventId", Integer.class);
        }
    }
}
