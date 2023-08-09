package com.example.todo.repository;

import com.example.todo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByEmail(String email);

    User findUserById(Long id);

    User findByUsername(String username);
    User findByUsernameOrEmail(String username, String email);
}
