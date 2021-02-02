package com.bicycle.racing.users;

import com.bicycle.racing.users.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
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
