package com.bicycle.racing.controller;

import com.bicycle.racing.model.Event;
import com.bicycle.racing.model.form.UserFileForm;
import com.bicycle.racing.service.EventService;
import com.bicycle.racing.service.PresenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;

@Controller
public class UploadFileController {

    private final PresenterService presenterService;
    private final EventService eventService;

    @Autowired
    public UploadFileController(PresenterService presenterService,
                                EventService eventService) {
        this.presenterService = presenterService;
        this.eventService = eventService;
    }

//    @PostMapping("/upload")
//    public String upload(@RequestParam("uploadfile") MultipartFile trackFile,
//                         @RequestParam("eventId") Integer eventId,
//                         Principal principal,
//                         Model model) {
//
//        UserFileForm userFileForm = new UserFileForm();
//        userFileForm.setUsername(principal.getName());
//        userFileForm.setTrackFile(trackFile);
//        userFileForm.setEventId(eventId);
//
//        if (presenterService.saveUserTrackByEventId(userFileForm)) {
//            model.addAttribute("result", "File load successfully");
//        } else {
//            model.addAttribute("error", "Fail load file");
//        }
//        Event event = eventService.getEventById(eventId);
//        model.addAttribute("event", event);
//
//        return "events/view_event";
//    }
}
