package com.example.flc.controller;

import org.springframework.stereotype.Controller;

import com.example.flc.service.CourseService;

@Controller
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

}
