package com.example.flc.controller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/client")
public class LCCController {

    // LIBRARY
    @GetMapping("/library")
    public String getLibraryPage() {
        return "client/library/library";
    }

    // COMMUNITY
    @GetMapping("/community")
    public String getCommunityPage() {
        return "client/community/community";
    }

    // COURSE
    @GetMapping("/course")
    public String getCoursePage() {
        return "client/course/course";
    }
}
