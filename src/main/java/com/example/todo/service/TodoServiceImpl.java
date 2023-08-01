package com.example.todo.service;

import com.example.todo.domain.Todo;
import com.example.todo.exceptions.TodoException;
import com.example.todo.repository.TodoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Todo getTodoById(Long id) {
        return todoRepository.getReferenceById(id);
    }

    @Override
    public List<Todo> getTodos() {
        return todoRepository.findAll();
    }

    @Override
    public Todo editTodo(Long id, String newTitle, String newDescription) throws TodoException {
        Todo existingTodo = getTodoById(id);
        if (existingTodo!=null){
            existingTodo.setTitle(newTitle);
            existingTodo.setDescription(newDescription);
            todoRepository.save(existingTodo);
        }else {
            throw new TodoException("Todo does not exist");
        }
        return null;
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
