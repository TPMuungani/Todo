package com.example.todo.controller;

import com.example.todo.domain.Todo;
import com.example.todo.exceptions.TodoException;
import com.example.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/todos")
@RequiredArgsConstructor
public class TodoController {
    private final TodoService todoService;

    @GetMapping
    public ResponseEntity<List<Todo>> getAllTodos(){
        return new ResponseEntity<>(todoService.getTodos(), HttpStatus.OK);
    }

//    api/v1/todos/1
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Todo>> getTodoById(@PathVariable("id") Long id){
        return new ResponseEntity<>(todoService.getTodoById(id), HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Todo> addTodo(@RequestBody Todo todo){
        return new ResponseEntity<>(todoService.addTodo(todo), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public void deleteTodo(@PathVariable("id") Long id){
        todoService.deleteTodo(id);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Optional<Todo>> editTodo(@PathVariable("id") Long id, @RequestBody Todo todo) throws TodoException {
        return new ResponseEntity<>(todoService.editTodo(id, todo.getTitle(), todo.getDescription()), HttpStatus.OK);
        //return null;
    }
}
