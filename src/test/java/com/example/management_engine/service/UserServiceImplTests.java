package com.example.management_engine.service;

import com.example.management_engine.domain.User;
import com.example.management_engine.exceptions.TodoException;
import com.example.management_engine.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTests {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    //Requirements - Return an object of a user after successfully added a new user
    @Test
    public void testAddUser() throws TodoException {
        //Arrange
        User user = new User();
        user.setUsername("Test");
        user.setLast_name("Test");
        user.setFirst_name("Test");
        user.setEmail("test@gmail.com");

        //Mock Behaviour
        Mockito.when(userRepository.save(user)).thenReturn(user);

        User actualUser = userService.addUser(user);

        //Assert
        assertEquals(actualUser, user);
        assertNotNull(actualUser);
    }

    //Requirements - Modify user data and return user object with modified data
//    @Test
//    public void testEditUser() throws UserException {
//        //Arrange
//        User user = new User();
//        user.setEmail("test@gmail.com");
//        user.setFirst_name("firstName");
//        user.setLast_name("lastName");
//        user.setUsername("username");
//
//        //Mock Behaviour
//        Mockito.when(userRepository.save(user)).thenReturn(user);
//
//        User actualUser = userService.editUser(user.getEmail(), user.getUsername(), user.getUsername(), user.getEmail(), user.getFirst_name(), user.getLast_name());
//
//        //Assert
//        assertNotEquals(actualUser, user);
//        assertNotNull(actualUser);
//    }


    //Requirements - return user object after using username and email as parameters

    @Test
    public void testFindByUsernameOrEmail(){
        //Arrange
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setFirst_name("firstName");
        user.setLast_name("lastName");
        user.setUsername("username");


        //Mock Behaviour
        Mockito.when(userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail())).thenReturn(user);

        User actualUser = userService.findByUsernameOrEmail(user.getUsername(), user.getEmail());

        //Assert
        assertNotNull(actualUser);
        assertEquals(user, actualUser);
    }

    //Requirements - return user object after using id as parameter

    @Test
    public void testFindUserById(){
        //Arrange
        User user = new User();
        user.setId(1L);
        user.setEmail("test@gmail.com");
        user.setFirst_name("firstName");
        user.setLast_name("lastName");
        user.setUsername("username");


        //Mock Behaviour
        Mockito.when(userRepository.findUserById(1L)).thenReturn(user);

        User actualUser = userService.findUserById(1L);

        //Assert
        assertNotNull(actualUser);
        assertEquals(user, actualUser);
    }

    //Requirements - return user object after using email as parameter

    @Test
    public void testFindUserByEmail(){
        //Arrange
        User user = new User();
        user.setId(1L);
        user.setEmail("test@gmail.com");
        user.setFirst_name("firstName");
        user.setLast_name("lastName");
        user.setUsername("username");


        //Mock Behaviour
        Mockito.when(userRepository.findUserByEmail(user.getEmail())).thenReturn(user);

        User actualUser = userService.findUserByEmail(user.getEmail());

        //Assert
        assertNotNull(actualUser);
        assertEquals(user, actualUser);
    }

    //Requirements - return user object after using email as parameter

    @Test
    public void testFindUserByUsername(){
        //Arrange
        User user = new User();
        user.setId(1L);
        user.setEmail("test@gmail.com");
        user.setFirst_name("firstName");
        user.setLast_name("lastName");
        user.setUsername("username");


        //Mock Behaviour
        Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);

        User actualUser = userService.findUserByUsername(user.getUsername());

        //Assert
        assertNotNull(actualUser);
        assertEquals(user, actualUser);
    }

    //Requirements - return all users

    @Test
    public void testListUsers(){
        //Arrange
        User user = new User();
        user.setId(1L);
        user.setEmail("test@gmail.com");
        user.setFirst_name("firstName");
        user.setLast_name("lastName");
        user.setUsername("username");


        //Mock Behaviour
        Mockito.when(userRepository.findAll()).thenReturn(List.of(user));

        List<User> actualUsers = userService.listUsers();

        //Assert
        assertNotNull(actualUsers);
        assertEquals(List.of(user), actualUsers);
    }

}
