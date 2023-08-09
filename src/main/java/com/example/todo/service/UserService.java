package com.example.todo.service;


import com.example.todo.domain.User;
import com.example.todo.exceptions.TodoException;
import com.example.todo.exceptions.UserException;

import java.util.List;

public interface UserService {
    User addUser(User user) throws TodoException;
    User editUser(String existingEmail, String existingUsername, String newUsername, String newEmail, String newFirstName, String newLastName) throws UserException;

    User findByUsernameOrEmail(String existingUsername, String existingEmail);

    User findUserById(Long id);
    User findUserByEmail(String email);
    User findUserByUsername(String username);
    List<User> listUsers();
    void deleteUser(Long id);
}
