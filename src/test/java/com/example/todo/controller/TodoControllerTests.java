package com.example.todo.controller;


import com.example.todo.domain.Todo;
import com.example.todo.service.TodoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

//    @GetMapping("/{id}")
//    public ResponseEntity<Todo> getTodoById(@PathVariable("id") Long id){
//        return new ResponseEntity<>(todoService.getTodoById(id), HttpStatus.OK);
//    }

    @Test
    public void testGetTodoById(){

        Long id = 1L;
        //Arrange
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setDescription("test add todo method");
        todo.setTitle("Unit Tests");

        //Mock Behaviour
        Mockito.when(todoService.getTodoById(id)).thenReturn(todo);

        ResponseEntity<Todo> actualTodo = todoController.getTodoById(id);

//        assert
        assertEquals(HttpStatus.OK, actualTodo.getStatusCode());
        assertTrue(Objects.equals(actualTodo.getBody().getId(), id));


    }


}
