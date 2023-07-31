package com.example.todo.service;


import com.example.todo.domain.Todo;
import com.example.todo.repository.TodoRepository;
import com.example.todo.repository.UserRepository;
import com.example.todo.service.TodoServiceImpl;
import com.example.todo.service.UserServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceImplTests {

//    contain the unit tests of Todo Service

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoServiceImpl todoService;

//       @Override
//    public Todo addTodo(Todo todo) {
//        Todo todo1 = new Todo();
//        todo1.setDescription(todo.getDescription());
//        todo1.setTitle(todo.getTitle());
//        return todoRepository.save(todo1);
//    }

    @Test
    public void testAddTodo(){

//        arrange

        Todo todo = new Todo();
        todo.setDescription("test add todo method");
        todo.setTitle("Unit Tests");

//        Mock behaviour/ Stubbing
        Mockito.when(todoRepository.save(todo)).thenReturn(todo);

        Todo actualTodo = todoService.addTodo(todo);

//        assert
        assertNotNull(actualTodo);
        assertEquals(todo, actualTodo);


    }

//    Requirements - Get all the todos

    @Test
    public void testGetTodos(){
//        arrange
        Todo todo = new Todo();
        todo.setDescription("test add todo method");
        todo.setTitle("Unit Tests");

//        Mock Behaviour
        Mockito.when(todoRepository.findAll()).thenReturn(List.of(todo));

        List<Todo> todosResult = todoService.getTodos();

//        assert
        assertNotNull(todosResult);
        assertEquals(List.of(todo), todosResult);

    }









}
