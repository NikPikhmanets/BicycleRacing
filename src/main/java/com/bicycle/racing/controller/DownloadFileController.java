package com.bicycle.racing.controller;

import com.bicycle.racing.service.DownloadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@RestController
//@RequestMapping("/files")
public class DownloadFileController {

    private final DownloadFileService fileService;

    @Autowired
    public DownloadFileController(DownloadFileService fileService) {
        this.fileService = fileService;
    }

//    @GetMapping("/download")
//    public void downloadFile(@RequestParam(value = "eventId") Integer eventId,
//                             @RequestParam(value = "type") String type,
//                             HttpServletResponse response) throws IOException {
//
//        fileService.downloadFile(eventId, type, response);
//    }
}
