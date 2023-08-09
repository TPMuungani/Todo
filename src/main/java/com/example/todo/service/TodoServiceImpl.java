package com.example.todo.service;

import com.example.todo.domain.Todo;
import com.example.todo.exceptions.TodoException;
import com.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;

    public TodoServiceImpl(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @Override
    public Todo addTodo(Todo todo) {
        Todo todo1 = new Todo();
        todo1.setDescription(todo.getDescription());
        todo1.setTitle(todo.getTitle());
        return todoRepository.save(todo1);
    }

    @Override
    public Optional<Todo> getTodoById(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Optional<Todo> editTodo(Long id, String newTitle, String newDescription) throws TodoException {
        Optional<Todo> existingTodo = getTodoById(id);
        if (existingTodo.isPresent()){
            existingTodo.get().setDescription(newDescription);
            existingTodo.get().setTitle(newTitle);
            todoRepository.save(existingTodo.get());
        }else {
            //throw new TodoException("Todo does not exist");
        }
        return existingTodo;
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }


    private List<Todo> getATodoWithoutAUserAttachedToIt(){
        List<Todo> todos = new ArrayList<>();
        for (Todo todo : todoRepository.findAll()){
            if (todo.getUser_id() == null){
                todos.add(todo);
            }
        }
        return todos;
    }
}
