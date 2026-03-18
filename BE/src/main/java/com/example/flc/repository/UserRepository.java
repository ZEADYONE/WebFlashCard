package com.example.flc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.flc.domain.User;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User save(User user);

    User findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByUserName(String userName);
}
