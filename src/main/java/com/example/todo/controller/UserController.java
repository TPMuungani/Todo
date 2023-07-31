package com.example.todo.controller;

import com.example.todo.domain.User;
import com.example.todo.exceptions.TodoException;
import com.example.todo.exceptions.UserException;
import com.example.todo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = {"/", "/user"})
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers(){
        return new ResponseEntity<>(userService.listUsers(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id){
        return new ResponseEntity<>(userService.findUserById(id), HttpStatus.OK);
    }
    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable("username") String username){
        return new ResponseEntity<>(userService.findUserByUsername(username), HttpStatus.OK);
    }
    @GetMapping("/{email}")
    public ResponseEntity<User> getUserByEmail(@PathVariable("email") String email){
        return new ResponseEntity<>(userService.findUserByEmail(email), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody User user) throws TodoException {
        return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
    }
    @PutMapping(path = {"/{username}", "/{email}"})
    public ResponseEntity<User> editUser(@PathVariable("email") String email, @PathVariable("username") String username, @RequestBody User user) throws UserException {
        return new ResponseEntity<>(userService.editUser(email, username, user.getFirst_name(), user.getLast_name(), user.getUsername(), user.getEmail()), HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
    }
}
