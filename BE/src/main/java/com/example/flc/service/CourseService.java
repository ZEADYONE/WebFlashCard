package com.example.flc.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.flc.domain.Deck;
import com.example.flc.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Deck> getAllDeck() {
        return this.courseRepository.findAllDecksByAdmin();
    }

}
