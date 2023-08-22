package com.example.management_engine.controller;

import com.example.management_engine.data.TodoDTO;
import com.example.management_engine.domain.Department;
import com.example.management_engine.domain.Todo;
import com.example.management_engine.domain.User;
import com.example.management_engine.enums.ProgressCheck;
import com.example.management_engine.exceptions.TodoException;
import com.example.management_engine.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("api/v1/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos(){
        return new ResponseEntity<>(todoService.getTodos(), OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Todo>> getTodoById(@PathVariable("id") Long id){
        return new ResponseEntity<>(todoService.getTodoById(id), OK);
    }
    @PostMapping
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo){
        return new ResponseEntity<>(todoService.addTodo(todo), CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable("id") Long id){
        todoService.deleteTodo(id);
        return new ResponseEntity<>(OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Todo>> editTodo(@PathVariable("id") Long id, @RequestBody Todo todo) throws TodoException {
        return new ResponseEntity<>(todoService.editTodo(id, todo), OK);
    }
    @GetMapping("/department")
    public ResponseEntity<List<Todo>> getTodoByDepartment(@RequestBody String department){
        List<Todo> todoList = todoService.findByDepartment(department);
        return new ResponseEntity<>(todoList, OK);
    }
    @GetMapping("/user")
    public ResponseEntity<List<Todo>> getTodoByUser(@RequestBody User user){
        List<Todo> todoList = todoService.findByUser(user);
        return new ResponseEntity<>(todoList, OK);
    }
    @GetMapping("/department-progress")
    public ResponseEntity<List<Todo>> getTodoByDepartmentAndProgressCheck(@RequestBody Department department, @RequestBody ProgressCheck progressCheck){
        List<Todo> todoList = todoService.findByDepartmentAndProgressCheck(department, progressCheck);
        return new ResponseEntity<>(todoList, OK);
    }
    @GetMapping("/department-user")
    public ResponseEntity<List<Todo>> getTodoByDepartmentAndUser(@RequestBody Department department, @RequestBody User user){
        List<Todo> todoList = todoService.findByDepartmentAndUser(department, user);
        return new ResponseEntity<>(todoList, OK);
    }

    @GetMapping("/no-user")
    public ResponseEntity<List<Todo>> getTodoWithoutUser(){
        List<Todo> todoList = todoService.getATodoWithoutAUserAttachedToIt();
        return new ResponseEntity<>(todoList, OK);
    }

    @GetMapping("/progress")
    public ResponseEntity<List<Todo>> getTodoByProgress(@RequestBody ProgressCheck progressCheck){
        List<Todo> todoList = todoService.getTodoByProgressCheck(progressCheck);
        return new ResponseEntity<>(todoList, OK);
    }

}
