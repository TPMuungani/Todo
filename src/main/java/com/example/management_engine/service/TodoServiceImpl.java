package com.example.management_engine.service;

import com.example.management_engine.data.TodoDTO;
import com.example.management_engine.domain.Department;
import com.example.management_engine.domain.SubTasks;
import com.example.management_engine.domain.Todo;
import com.example.management_engine.domain.User;
import com.example.management_engine.enums.ProgressCheck;
import com.example.management_engine.exceptions.TodoException;
import com.example.management_engine.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.example.management_engine.enums.ProgressCheck.*;
import static java.math.BigDecimal.ZERO;

@Service
@Slf4j
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{
    private final TodoRepository todoRepository;

    @Override
    public Todo addTodo(Todo todo) {
        Todo todo1 = new Todo();
        todo1.setStartDate(new Date());
        todo1.setDescription(todo.getDescription());
        todo1.setTitle(todo.getTitle());
        todo1.setSubTasks(todo.getSubTasks());
        todo1.setDepartment(todo.getDepartment());
        todo1.setUser(todo.getUser());
        todo1.setProgressCheck(OPEN);
        todo1.setPercentageProgress(ZERO);
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
    public Optional<Todo> editTodo(Long id, Todo todo) throws TodoException {
        Optional<Todo> existingTodo = getTodoById(id);
        if (existingTodo.isPresent()){
            validateDates(existingTodo.get());
            existingTodo.get().setStartDate(todo.getStartDate());
            existingTodo.get().setFinishDate(todo.getFinishDate());
            existingTodo.get().setDescription(todo.getDescription());
            existingTodo.get().setTitle(todo.getTitle());
            existingTodo.get().setUser(todo.getUser());
            existingTodo.get().setDepartment(todo.getDepartment());
            existingTodo.get().setSubTasks(todo.getSubTasks());
            checkSubTasksProgress(existingTodo.get());
            checkATodoProgress(existingTodo.get());
            todoRepository.save(existingTodo.get());
        }else {
            throw new TodoException("Todo does not exist");
        }
        return existingTodo;
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.deleteById(id);
    }

    @Override
    public List<Todo> findByDepartment(String department) {
        return todoRepository.findByDepartment(department);
    }

    @Override
    public List<Todo> findByUser(User user) {
        return todoRepository.findByUser(user);
    }

    @Override
    public List<Todo> findByDepartmentAndProgressCheck(Department department, ProgressCheck progressCheck) {
        return todoRepository.findByDepartmentAndProgressCheck(department, progressCheck);
    }

    @Override
    public List<Todo> findByDepartmentAndUser(Department department, User user) {
        return todoRepository.findByDepartmentAndUser(department, user);
    }

    @Override
    public List<Todo> getATodoWithoutAUserAttachedToIt(){
        List<Todo> todos = new ArrayList<>();
        for (Todo todo : todoRepository.findAll()){
            if (todo.getUser() == null){
                todos.add(todo);
            }
        }
        return todos;
    }

    @Override
    public List<Todo> getTodoByProgressCheck(ProgressCheck progressCheck) {
        return todoRepository.findByProgressCheck(progressCheck);
    }

    private Todo checkATodoProgress(Todo todo){
        if (todo.getProgressCheck().equals(COMPLETE)){
            log.error("Todo information status: Already completed and needs to be closed");
            return todo;
        }
        if (todo.getPercentageProgress().compareTo(ZERO)>0){
            todo.setProgressCheck(IN_PROGRESS);
        }

        return todo;
    }

    private Todo checkSubTasksProgress(Todo todo){
        List<SubTasks> subTasks = todo.getSubTasks();
        int tasksCount = subTasks.size();
        int completedTasks = 0;
        if (subTasks.isEmpty()){
            return todo;
        }else {
            for (SubTasks task : subTasks){
                if (task.getProgressCheck().equals(COMPLETE)){
                    completedTasks = completedTasks + 1;
                }
            }
            if (tasksCount==completedTasks){
                todo.setPercentageProgress(BigDecimal.valueOf(100));
                todo.setProgressCheck(COMPLETE);
            }
            todo.setPercentageProgress(calculateProgressPercentage(completedTasks, tasksCount));

        }
        return todo;
    }

    private BigDecimal calculateProgressPercentage(int completedTasks, int tasksCount){
        return BigDecimal.valueOf(completedTasks).divide(BigDecimal.valueOf(tasksCount))
                .multiply(BigDecimal.valueOf(100));
    }

    private void validateDates(Todo todo){
        if (todo.getFinishDate().after(new Date())){
            todo.setProgressCheck(OVERDUE);
            log.error("Todo information: This todo is overdue.");
        }
        Period period = Period.between(todo.getLastModifiedDate().toLocalDate(), LocalDate.now());
        if (period.getDays() >= 5){
            todo.setProgressCheck(ON_HOLD);
            log.info("Todo information: This todo has not been updated for 5 days so it has been automatically put onhold.");
        }
    }
}
