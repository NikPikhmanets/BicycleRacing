package com.bicycle.racing.controller;

import com.bicycle.racing.service.DownloadFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
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
