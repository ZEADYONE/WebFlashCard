package com.example.flc.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.flc.domain.Deck;
import com.example.flc.repository.CourseRepository;

@Service
public class CourseService {

    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Page<Deck> getAllDeck(Pageable pageable) {
        return this.courseRepository.findAllDecksByAdmin(pageable);
    }

}
