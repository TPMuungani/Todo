package com.example.management_engine.repository;

import com.example.management_engine.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);

    User findUserById(Long id);

    User findByUsername(String username);
    User findByUsernameOrEmail(String username, String email);
    List<User> findByDepartment(String department);
}
