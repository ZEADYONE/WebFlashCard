package com.example.flc.controller.admin;

import org.springframework.stereotype.Controller;

import com.example.flc.service.admin.CourseService;

@Controller
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

}
