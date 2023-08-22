package com.example.management_engine.service;


import com.example.management_engine.data.TodoDTO;
import com.example.management_engine.domain.Department;
import com.example.management_engine.domain.Todo;
import com.example.management_engine.domain.User;
import com.example.management_engine.enums.ProgressCheck;
import com.example.management_engine.exceptions.TodoException;
import com.example.management_engine.repository.TodoRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

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


        //Mock behaviour
        Mockito.when(todoRepository.save(todo)).thenReturn(todo);

        Optional<Todo> actualTodo = todoService.editTodo(todo.getId(), todo);

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


    //Requrememnts - Get A Todo by User attached to it

    @Test
    public void testFindTodoByUser(){
        //Arrange User
        User user = new User();
        user.setUsername("tmuungani");

        //Arrange Todo
        Todo todo = new Todo();
        todo.setId(1L);
        todo.setTitle("Delete todo");
        todo.setDescription("delete");
        todo.setUser(user);

        //Mock Behaviour
        Mockito.when(todoRepository.findByUser(user)).thenReturn(List.of(todo));

        List<Todo> actualTodo = todoService.findByUser(user)
;
        //Assert
        assertNotNull(actualTodo);
        assertEquals(List.of(todo), actualTodo);


    }

    //Requrememnts - Get A Todo by Department

    @Test
    public void testFindTodoByDepartment(){
        //Arrange Department
        Department department1 = new Department();
        department1.setId(2L);
        department1.setName("Accounts");

        //Arrange User
        User user = new User();
        user.setUsername("tmuungani");
        user.setDepartment(department1);

        //Arrange Todo
        Todo todo = new Todo();
        user.setDepartment(department1);
        todo.setId(1L);
        todo.setTitle("Delete todo");
        todo.setDescription("delete");
        todo.setUser(user);
        todo.setDepartment(user.getDepartment());

        //Mock Behaviour
        Mockito.when(todoRepository.findByDepartment(department1.getName())).thenReturn(List.of(todo));

        List<Todo> actualTodo = todoService.findByDepartment(user.getDepartment().getName())
                ;
        //Assert
        assertNotNull(actualTodo);
        assertEquals(List.of(todo), actualTodo);
    }

    //Requirements - find a Todo using User and their department
    @Test
    public void testFindTodoByDepartmrntAndUser(){
        //Arrange Department
        Department department = new Department();
        department.setId(1L);
        department.setName("IT");

        //Arrange User
        User user = new User();
        user.setUsername("tmuungani");
        user.setDepartment(department);

        //Arrange Todo
        Todo todo = new Todo();
        todo.setDepartment(department);
        todo.setDescription("Test");

        //Mock Behaviour
        Mockito.when(todoRepository.findByDepartmentAndUser(department, user)).thenReturn(List.of(todo));

        List<Todo> actualTodoList = todoService.findByDepartmentAndUser(department, user);

        //Assert
        assertEquals(List.of(todo), actualTodoList);
        assertNotNull(actualTodoList);
    }




    //Requirements - find a todo using Department And progress check
    @Test
    public void testFindTodoByDepartmentAndProgressCheck(){
        //Arrange Department
        Department department = new Department();
        department.setName("Business");

        //Arrange Todo
        Todo todo = new Todo();
        todo.setDescription("Construct an income statement for the year 2021");
        todo.setDepartment(department);
        todo.setProgressCheck(ProgressCheck.COMPLETE);

        //Mock Behaviour
        Mockito.when(todoRepository.findByDepartmentAndProgressCheck(department, ProgressCheck.COMPLETE)).thenReturn(List.of(todo));

        List<Todo> actualTodo = todoService.findByDepartmentAndProgressCheck(department, ProgressCheck.COMPLETE);

        //Assert
        assertNotNull(actualTodo);
        assertEquals(List.of(todo), actualTodo);
    }

    //Requirements - Get a todo without any user attached to it
    @Test
    public void testGetATodoWithoutAUserAttachedToIt(){
        //Arrange - Department
        Department department = new Department();
        department.setName("Sports");

        //Arrange - User
        User user = new User();
        user.setDepartment(department);
        user.setUsername("tmuungani");

        //Arrange
        Todo todo = new Todo();
        todo.setDescription("Fix computers");
        todo.setDepartment(department);
        //todo.setUser(user);

        //Mock Behaviour
        Mockito.when(todoService.getATodoWithoutAUserAttachedToIt()).thenReturn(List.of(todo));

        List<Todo> actualTodos = todoService.getATodoWithoutAUserAttachedToIt();

        //Assert
        assertEquals(actualTodos, List.of(todo));
        assertNotNull(actualTodos);
    }

    //Requirements - find a todo using progress check
    @Test
    public void testFindTodoByProgressCheck(){
        //Arrange Department
        Department department = new Department();
        department.setName("Business");

        //Arrange Todo
        Todo todo = new Todo();
        todo.setDescription("Construct an income statement for the year 2021");
        todo.setDepartment(department);
        todo.setProgressCheck(ProgressCheck.COMPLETE);

        //Mock Behaviour
        Mockito.when(todoRepository.findByProgressCheck(ProgressCheck.COMPLETE)).thenReturn(List.of(todo));

        List<Todo> actualTodo = todoService.getTodoByProgressCheck(ProgressCheck.COMPLETE);

        //Assert
        assertNotNull(actualTodo);
        assertEquals(List.of(todo), actualTodo);
    }


}
