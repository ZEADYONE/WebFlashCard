package com.example.flc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.flc.domain.Deck;

@Repository
public interface CourseRepository extends JpaRepository<Deck, Long> {

    @Query("SELECT d FROM Deck d WHERE d.user.role.name = 'ADMIN'")
    List<Deck> findAllDecksByAdmin();
}
