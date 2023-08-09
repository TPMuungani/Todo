package com.example.todo.service;


import com.example.todo.domain.Todo;
import com.example.todo.exceptions.TodoException;
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

import static org.junit.jupiter.api.Assertions.*;

@RunWith(MockitoJUnitRunner.class)
public class TodoServiceImplTests {

//    contain the unit tests of Todo Service

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private TodoServiceImpl todoService;


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



    //Requirements - Update a todo and get the updated todo object
    @Test
    public void testEditTodo() throws TodoException {
        //arrange
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setTitle("test todo edit method");
        todo.setDescription("Unit test for edit");
        todo.setUser_id(3L);

        //Mock behaviour
        Mockito.when(todoRepository.save(todo)).thenReturn(todo);

        Optional<Todo> actualTodo = todoService.editTodo(todo.getId(), todo.getTitle(), todo.getDescription());

        //assert
        assertNotEquals(actualTodo, todo);
        assertNotNull(actualTodo);

    }

    //Requirements - Get a Todo using id as a parameter
    @Test
    public void testGetTodoById(){
        //arrange

        Todo todo = new Todo();
        todo.setId(1L);
        todo.setTitle("test todo edit method");
        todo.setDescription("Unit test for edit");

        //mock behaviour
        Mockito.when(todoRepository.getById(1L)).thenReturn(todo);

        Optional<Todo> actualTodo = todoService.getTodoById(1L);

        //assert
        assertNotNull(actualTodo);
        //assertEquals(todo,actualTodo);
    }

//    @Test
//    public void testDeleteTodo(){
//        //arrange
//        Todo todo = new Todo();
//        todo.setId(1L);
//        todo.setTitle("Delete todo");
//        todo.setDescription("delete");
//
//        //Mock behaviour
//        Mockito.when(todoRepository.deleteById(1L)).thenReturn;
//
//
//        todoService.deleteTodo(1L);
//
//        assertEquals(todoService, todo);
//    }








}
