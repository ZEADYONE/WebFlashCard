package com.example.flc.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.flc.domain.Card;
import com.example.flc.domain.Progress;
import com.example.flc.domain.User;

public interface ProgressRepository extends JpaRepository<Progress, Long> {
    Optional<Progress> findByUserAndCard(User user, Card card);
}
