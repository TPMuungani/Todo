package com.example.todo.service;

import com.example.todo.domain.User;
import com.example.todo.exceptions.TodoException;
import com.example.todo.exceptions.UserException;
import com.example.todo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    @Override
    public User addUser(User user) throws TodoException {
        validateUser(user);
        User newUser = new User();
        newUser.setFirst_name(user.getFirst_name());
        newUser.setLast_name(user.getLast_name());
        newUser.setEmail(user.getEmail());
        return userRepository.save(newUser);
    }

    @Override
    public User editUser(String existingEmail, String existingUsername, String newUsername, String newEmail, String newFirstName, String newLastName) throws UserException {
        User oldUser = findUserByEmail(existingEmail);
        User oldUser1 = findUserByUsername(existingUsername);
        if (oldUser!=null || oldUser1!=null){
            oldUser.setFirst_name(newFirstName);
            oldUser.setLast_name(newLastName);
            oldUser.setEmail(newEmail);
            oldUser.setUsername(newUsername);
            userRepository.save(oldUser);
        }
        throw new UserException("User not found");
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmal(email);
    }

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
    private User validateUser(User user) throws TodoException {
        Optional<User> existingUser = Optional.ofNullable(findUserByEmail(user.getEmail()));
        if (existingUser.isPresent()){
            throw new TodoException("Email already exist");
        }
        Optional<User> userByUsername = Optional.ofNullable(findUserByUsername(user.getUsername()));
        if (userByUsername.isPresent()){
            throw new TodoException("Username already in use");
        }
        return null;
    }
}
