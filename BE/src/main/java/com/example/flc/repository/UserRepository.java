package com.example.flc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.flc.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

}
