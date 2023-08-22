package com.example.management_engine.controller;


import com.example.management_engine.domain.Department;
import com.example.management_engine.domain.Todo;
import com.example.management_engine.domain.User;
import com.example.management_engine.enums.ProgressCheck;
import com.example.management_engine.exceptions.TodoException;
import com.example.management_engine.service.TodoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static javax.swing.text.html.HTML.Tag.U;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class TodoControllerTests {


    @Mock
    private TodoService todoService;

    @InjectMocks
    private TodoController todoController;


    //Requirement - implement an endpoint that get all todos
//    Produce an Http Status of OK
    @Test
    public void testGetAllTodos(){
//    arrange
        Todo todo = new Todo();
        todo.setDescription("test add todo method");
        todo.setTitle("Unit Tests");

        Todo todo1 = new Todo();
        todo1.setDescription("test add todo method");
        todo1.setTitle("Unit Tests");

//        Mock Behaviour
        Mockito.when(todoService.getTodos()).thenReturn(List.of(todo1, todo));


        ResponseEntity<List<Todo>> actualAllTodos = todoController.getAllTodos();

//        asserts
        assertEquals(HttpStatus.OK, actualAllTodos.getStatusCode());
        assertEquals(List.of(todo, todo1), actualAllTodos.getBody());


    }

    //Requirements - Get a todo using id

    @Test
    public void testGetTodoById(){

        Long id = 1L;
        //Arrange
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setDescription("test add todo method");
        todo.setTitle("Unit Tests");



        //Mock Behaviour
        Mockito.when(todoService.getTodoById(id)).thenReturn(Optional.of(todo));

        ResponseEntity<Optional<Todo>> actualTodo = todoController.getTodoById(id);

//        assert
        assertEquals(HttpStatus.OK, actualTodo.getStatusCode());
        assertTrue(Objects.equals(actualTodo.getBody().get().getId(), id));


    }

    //Requirements - add a new todo and return Http status Created
    @Test
    public void testAddTodo(){
        //Arrange
        Todo todo = new Todo();
        todo.setDescription("New Todo");
        todo.setTitle("New todo");

        //Mock behaviour
        Mockito.when(todoService.addTodo(todo)).thenReturn(todo);

        ResponseEntity<Todo> actualTodo = todoController.addTodo(todo);


        //Assert
        assertEquals(HttpStatus.CREATED, actualTodo.getStatusCode());
        assertNotNull(actualTodo);
    }

    //Requirements - delete a todo
//    @Test
//    public void testDeleteTodo(){
//        //arrange
//        Todo todo = new Todo();
//        todo.setId(1L);
//        todo.setTitle("Delete");
//        todo.setDescription("Description");
//
//        //Mock Behaviour
//        Mockito.when(todoService.deleteTodo(1L)).thenReturn;
//    }

    //Requirements - Edit a todo and return HttpStatus-Ok
    @Test
    public void testEditTodo() throws TodoException {
        //Arrange
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setDescription("Edit");
        todo.setTitle("Edit");

        //Mock Behaviour
        Mockito.when(todoService.editTodo(1L, todo)).thenReturn(Optional.of(todo));

        ResponseEntity<Optional<Todo>> actualTodo = todoController.editTodo(1L, todo);

        //Assert
        assertEquals(actualTodo.getStatusCode(), HttpStatus.OK);
        assertNotNull(actualTodo);
    }
    //Requirements - get a todo by department and return HttpStatus-Ok
    @Test
    public void testGetTodoByDepartment() throws TodoException {
        //arrange
        Department department = new Department();
        department.setId(2L);
        department.setName("Data Analytics");

        //Arrange
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setDescription("Test");
        todo.setTitle("Test");
        todo.setDepartment(department);

        //Mock Behaviour
        Mockito.when(todoService.findByDepartment(department.getName())).thenReturn(List.of(todo));

        ResponseEntity<List<Todo>> actualTodo = todoController.getTodoByDepartment(department.getName());

        //Assert
        assertEquals(actualTodo.getStatusCode(), HttpStatus.OK);
        assertNotNull(actualTodo);
    }

    //Requirements - get a todo by user and return HttpStatus-Ok
    @Test
    public void testGetTodoByUser() throws TodoException {
        //arrange
        Department department = new Department();
        department.setId(2L);
        department.setName("Data Analytics");

        //Arrange
        User user = new User();
        user.setUsername("tmuungani");
        user.setDepartment(department);

        //Arrange
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setDescription("Test");
        todo.setTitle("Test");
        todo.setDepartment(department);
        todo.setUser(user);

        //Mock Behaviour
        Mockito.when(todoService.findByUser(user)).thenReturn(List.of(todo));

        ResponseEntity<List<Todo>> actualTodo = todoController.getTodoByUser(user);

        //Assert
        assertEquals(actualTodo.getStatusCode(), HttpStatus.OK);
        assertNotNull(actualTodo);
    }

    //Requirements - get a todo by department and Progress check and return HttpStatus-Ok
    @Test
    public void testGetTodoByDepartmentAndProgressCheck() throws TodoException {
        //arrange
        Department department = new Department();
        department.setId(2L);
        department.setName("Data Analytics");

        //Arrange
        User user = new User();
        user.setUsername("tmuungani");
        user.setDepartment(department);

        //Arrange
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setDescription("Test");
        todo.setTitle("Test");
        todo.setDepartment(department);
        todo.setProgressCheck(ProgressCheck.ON_HOLD);
        todo.setUser(user);

        //Mock Behaviour
        Mockito.when(todoService.findByDepartmentAndProgressCheck(department, ProgressCheck.ON_HOLD)).thenReturn(List.of(todo));

        ResponseEntity<List<Todo>> actualTodo = todoController.getTodoByDepartmentAndProgressCheck(department, ProgressCheck.ON_HOLD);

        //Assert
        assertEquals(actualTodo.getStatusCode(), HttpStatus.OK);
        assertNotNull(actualTodo);
    }

    //Requirements - get a todo by department and user and return HttpStatus-Ok
    @Test
    public void testGetTodoByDepartmentAndUser() throws TodoException {
        //arrange
        Department department = new Department();
        department.setId(2L);
        department.setName("Data Analytics");

        //Arrange
        User user = new User();
        user.setUsername("tmuungani");
        user.setDepartment(department);

        //Arrange
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setDescription("Test");
        todo.setTitle("Test");
        todo.setDepartment(department);
        todo.setProgressCheck(ProgressCheck.ON_HOLD);
        todo.setUser(user);

        //Mock Behaviour
        Mockito.when(todoService.findByDepartmentAndUser(department, user)).thenReturn(List.of(todo));

        ResponseEntity<List<Todo>> actualTodo = todoController.getTodoByDepartmentAndUser(department, user);

        //Assert
        assertEquals(actualTodo.getStatusCode(), HttpStatus.OK);
        assertNotNull(actualTodo);
    }

    //Requirements - get a todo by Progress check and return HttpStatus-Ok
    @Test
    public void testGetTodoByProgressCheck() throws TodoException {
        //arrange
        Department department = new Department();
        department.setId(2L);
        department.setName("Data Analytics");

        //Arrange
        User user = new User();
        user.setUsername("tmuungani");
        user.setDepartment(department);

        //Arrange
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setDescription("Test");
        todo.setTitle("Test");
        todo.setDepartment(department);
        todo.setProgressCheck(ProgressCheck.ON_HOLD);
        todo.setUser(user);

        //Mock Behaviour
        Mockito.when(todoService.getTodoByProgressCheck(ProgressCheck.ON_HOLD)).thenReturn(List.of(todo));

        ResponseEntity<List<Todo>> actualTodo = todoController.getTodoByProgress(ProgressCheck.ON_HOLD);

        //Assert
        assertEquals(actualTodo.getStatusCode(), HttpStatus.OK);
        assertNotNull(actualTodo);
    }

    //Requirements - get a todo by not assigned to user and return HttpStatus-Ok
    @Test
    public void testGetTodoWithoutUsers() throws TodoException {
        //arrange
        Department department = new Department();
        department.setId(2L);
        department.setName("Data Analytics");

        //Arrange
        User user = new User();
        user.setUsername("tmuungani");
        user.setDepartment(department);

        //Arrange
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setDescription("Test");
        todo.setTitle("Test");
        todo.setDepartment(department);
        todo.setProgressCheck(ProgressCheck.ON_HOLD);
        todo.setUser(user);

        //Mock Behaviour
        Mockito.when(todoService.getATodoWithoutAUserAttachedToIt()).thenReturn(List.of(todo));

        ResponseEntity<List<Todo>> actualTodo = todoController.getTodoWithoutUser();

        //Assert
        assertEquals(actualTodo.getStatusCode(), HttpStatus.OK);
        assertNotNull(actualTodo);
    }

}
