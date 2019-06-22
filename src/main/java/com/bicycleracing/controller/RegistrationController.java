package com.bicycleracing.controller;

import com.bicycleracing.model.form.RegistrationForm;
import com.bicycleracing.service.SecurityService;
import com.bicycleracing.service.UserService;
import com.bicycleracing.validator.RegistrationValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegistrationController {

    private final UserService userService;
    private final SecurityService securityService;
    private final RegistrationValidator registrationValidator;

    @Autowired
    public RegistrationController(UserService userService,
                                  SecurityService securityService,
                                  RegistrationValidator registrationValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.registrationValidator = registrationValidator;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("registrationForm", new RegistrationForm());

        return "user/registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("registrationForm") RegistrationForm form,
                               BindingResult bindingResult) {
        registrationValidator.validate(form, bindingResult);

        if (bindingResult.hasErrors()) {
            return "user/registration";
        }
        userService.saveUser(form);
        securityService.autoLogin(form.getUsername(), form.getPasswordConfirm());

        return "redirect:/profile";
    }
}
