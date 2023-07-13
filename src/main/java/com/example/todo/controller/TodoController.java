package com.example.todo.controller;

import com.example.todo.domain.Todo;
import com.example.todo.exceptions.TodoException;
import com.example.todo.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/todo")
public class TodoController {
    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }
    @GetMapping("/all")
    public ResponseEntity<List<Todo>> getAllTodos(){
        return new ResponseEntity<>(todoService.getTodos(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable("id") Long id){
        return new ResponseEntity<>(todoService.getTodoById(id), HttpStatus.OK);
    }
    @PostMapping("/add")
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo){
        return new ResponseEntity<>(todoService.addTodo(todo), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable("id") Long id){
        todoService.deleteTodo(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Todo> editTodo(@PathVariable("id") Long id, @RequestBody Todo todo) throws TodoException {
        return new ResponseEntity<>(todoService.editTodo(id, todo.getTitle(), todo.getDescription()), HttpStatus.OK);
    }
}
