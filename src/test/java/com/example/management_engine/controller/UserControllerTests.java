package com.example.management_engine.controller;

import com.example.management_engine.domain.User;
import com.example.management_engine.exceptions.TodoException;
import com.example.management_engine.exceptions.UserException;
import com.example.management_engine.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTests {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testGetAllUsers(){
        //Arrange
        User user = new User();
        user.setUsername("ttest");
        user.setFirst_name("test");
        user.setLast_name("testtest");
        user.setEmail("test@gmail.com");
        user.setId(1L);

        //Mock behaviour
        Mockito.when(userService.listUsers()).thenReturn(List.of(user));

        ResponseEntity<List<User>> actualUsers = userController.getAllUsers();

        //Assert
        assertEquals(HttpStatus.OK, actualUsers.getStatusCode());
        assertNotNull(actualUsers);
    }

    @Test
    public void testGetUserById(){
        //Arrange
        User user = new User();
        user.setUsername("ttest");
        user.setFirst_name("test");
        user.setLast_name("testtest");
        user.setEmail("test@gmail.com");
        user.setId(1L);

        //Mock behaviour
        Mockito.when(userService.findUserById(1L)).thenReturn(user);

        ResponseEntity<User> actualUser = userController.getUserById(1L);

        //Assert
        assertEquals(HttpStatus.OK, actualUser.getStatusCode());
        assertNotNull(actualUser);
    }

    @Test
    public void testGetUserByUsername(){
        //Arrange
        User user = new User();
        user.setUsername("ttest");
        user.setFirst_name("test");
        user.setLast_name("testtest");
        user.setEmail("test@gmail.com");
        user.setId(1L);

        //Mock behaviour
        Mockito.when(userService.findUserByUsername("ttest")).thenReturn(user);

        ResponseEntity<User> actualUser = userController.getUserByUsername("ttest");

        //Assert
        assertEquals(HttpStatus.OK, actualUser.getStatusCode());
        assertNotNull(actualUser);
    }
    @Test
    public void testGetUserByEmail(){
        //Arrange
        User user = new User();
        user.setUsername("ttest");
        user.setFirst_name("test");
        user.setLast_name("testtest");
        user.setEmail("test@gmail.com");
        user.setId(1L);

        //Mock behaviour
        Mockito.when(userService.findUserByEmail("test@gmail.com")).thenReturn(user);

        ResponseEntity<User> actualUser = userController.getUserByEmail("test@gmail.com");

        //Assert
        assertEquals(HttpStatus.OK, actualUser.getStatusCode());
        assertNotNull(actualUser);
    }

    @Test
    public void testAddUser() throws UserException, TodoException {
        //Arrange
        User user = new User();
        user.setUsername("ttest");
        user.setFirst_name("test");
        user.setLast_name("testtest");
        user.setEmail("test@gmail.com");
        user.setId(1L);

        //Mock behaviour
        Mockito.when(userService.addUser(user)).thenReturn(user);

        ResponseEntity<User> actualUser = userController.addUser(user);

        //Assert
        assertEquals(HttpStatus.CREATED, actualUser.getStatusCode());
        assertNotNull(actualUser);
    }

    @Test
    public void testEditUser() throws UserException {
        //Arrange
        User user = new User();
        user.setUsername("ttest");
        user.setFirst_name("test");
        user.setLast_name("testtest");
        user.setEmail("test@gmail.com");
        user.setId(1L);

        //Mock behaviour
        Mockito.when(userService.editUser(user.getEmail(), user.getUsername(), user.getUsername(), user.getFirst_name(), user.getLast_name(), user.getLast_name())).thenReturn(user);

        ResponseEntity<User> actualUser = userController.editUser(user.getEmail(), user.getUsername(), user);

        //Assert
        assertEquals(HttpStatus.OK, actualUser.getStatusCode());
        assertNotNull(actualUser);
    }

//    @Test
//    public void testDeleteUser() throws UserException {
//        //Arrange
//        User user = new User();
//        user.setUsername("ttest");
//        user.setFirst_name("test");
//        user.setLast_name("testtest");
//        user.setEmail("test@gmail.com");
//        user.setId(1L);
//
//        //Mock behaviour
//        Mockito.when(userService.deleteUser(1L)).;
//
//        ResponseEntity<?> actualUser = userController.deleteUser(user.getId());
//
//        //Assert
//        assertEquals(HttpStatus.OK, actualUser.getStatusCode());
//        assertNotNull(actualUser);
//    }



}
