package com.example.management_engine.service;


import com.example.management_engine.domain.User;
import com.example.management_engine.exceptions.TodoException;
import com.example.management_engine.exceptions.UserException;

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
    List<User> findByDepartment(String department);
}
