package com.example.management_engine.service;

import com.example.management_engine.data.TodoDTO;
import com.example.management_engine.domain.Department;
import com.example.management_engine.domain.Todo;
import com.example.management_engine.domain.User;
import com.example.management_engine.enums.ProgressCheck;
import com.example.management_engine.exceptions.TodoException;

import java.util.List;
import java.util.Optional;

public interface TodoService {
    Todo addTodo(Todo todo);
    Optional<Todo> getTodoById(Long id);
    List<Todo> getTodos();
    Optional<Todo> editTodo(Long id, Todo todo) throws TodoException;
    void deleteTodo(Long id);
    List<Todo> findByDepartment(String department);
    List<Todo> findByUser(User user);
    List<Todo> findByDepartmentAndProgressCheck(Department department, ProgressCheck progressCheck);
    List<Todo> findByDepartmentAndUser(Department department, User user);
    List<Todo> getATodoWithoutAUserAttachedToIt();
    List<Todo> getTodoByProgressCheck(ProgressCheck progressCheck);
}
