package com.bicycle.racing.controller;

import com.bicycle.racing.service.EventRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    private final EventRegistrationService eventRegistrationService;

    @Autowired
    public UserController(EventRegistrationService eventRegistrationService) {
        this.eventRegistrationService = eventRegistrationService;
    }

//    @GetMapping("/profile")
//    public String profile(Model model, Principal principal) {
//        retrieveAndSetUser(model, principal);
//
//        return "user/profile";
//    }
//
//    @GetMapping(value = "/profile/{page}", produces = "application/json")
//    @ResponseBody
//    public Page<UserEvent> events(@PathVariable Integer page, Principal principal) {
//        String username = principal.getName();
//        return eventRegistrationService.getUserEventsPage(PageRequest.of(page, PAGE_SIZE), username);
//    }
//
//    @GetMapping(value = "/403")
//    public String accessDenied(Model model, Principal principal) {
//
//        if (principal != null) {
//            User user = (User) ((Authentication) principal).getPrincipal();
//            model.addAttribute("user", user.getUsername());
//            String message = "Hi " + principal.getName()
//                    + "! You do not have permission to access this page!";
//            model.addAttribute("message", message);
//        }
//        return "user/403Page";
//    }
//
//    private void retrieveAndSetUser(Model model, Principal principal) {
//        if (principal != null) {
//            User user = (User) ((Authentication) principal).getPrincipal();
//            model.addAttribute("user", "Welcome, " + user.getUsername());
//            model.addAttribute("eventId", Integer.class);
//        }
//    }
}
