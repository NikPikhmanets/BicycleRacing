package com.bicycle.racing.controller;

import com.bicycle.racing.service.SecurityService;
import com.bicycle.racing.users.UserServiceImpl;
import com.bicycle.racing.validator.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;

//@RestController
//@RequestMapping("/registration")
public class RegistrationController {

    private final UserServiceImpl userService;
    private final SecurityService securityService;
    private final RegistrationValidator registrationValidator;

    @Autowired
    public RegistrationController(UserServiceImpl userService,
                                  SecurityService securityService,
                                  RegistrationValidator registrationValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.registrationValidator = registrationValidator;
    }

//    @GetMapping("/registration")
//    public String registration(Model model) {
//        model.addAttribute("registrationForm", new RegistrationForm());
//
//        return "user/registration";
//    }
//
//    @PostMapping("/registration")
//    public String registration(@ModelAttribute("registrationForm") RegistrationForm form,
//                               BindingResult bindingResult) {
//        registrationValidator.validate(form, bindingResult);
//
//        if (bindingResult.hasErrors()) {
//            return "user/registration";
//        }
//        userService.saveUser(form);
//        securityService.autoLogin(form.getUsername(), form.getPasswordConfirm());
//
//        return "redirect:/profile";
//    }
}
