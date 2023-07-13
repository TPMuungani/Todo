package com.example.todo.service;

import com.example.todo.domain.Todo;
import com.example.todo.exceptions.TodoException;

import java.util.List;

public interface TodoService {
    Todo addTodo(Todo todo);
    Todo getTodoById(Long id);
    List<Todo> getTodos();
    Todo editTodo(Long id, String newTitle, String newDescription) throws TodoException;
    void deleteTodo(Long id);
}
