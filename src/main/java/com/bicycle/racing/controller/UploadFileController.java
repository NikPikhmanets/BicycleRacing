package com.bicycle.racing.controller;

import com.bicycle.racing.events.EventServiceImpl;
import com.bicycle.racing.service.PresenterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/upfile")
public class UploadFileController {

    private final PresenterService presenterService;
    private final EventServiceImpl eventService;

    @Autowired
    public UploadFileController(PresenterService presenterService,
                                EventServiceImpl eventService) {
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
