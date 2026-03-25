package com.example.flc.controller.client;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.flc.domain.Deck;
import com.example.flc.service.CourseService;

@Controller
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/admin/course")
    public String getCoursePage(Model model) {

        List<Deck> deck = this.courseService.getAllDeck();
        model.addAttribute("listCourse", deck);
        return "admin/course/course";
    }

}
