package com.example.management_engine.repository;

import com.example.management_engine.data.TodoDTO;
import com.example.management_engine.domain.Department;
import com.example.management_engine.domain.Todo;
import com.example.management_engine.domain.User;
import com.example.management_engine.enums.ProgressCheck;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<TodoDTO> findByDepartment(String department);
    List<TodoDTO> findByUser(User user);
    List<TodoDTO> findByProgressCheck(ProgressCheck progressCheck);
    List<TodoDTO> findByDepartmentAndProgressCheck(Department department, ProgressCheck progressCheck);
    List<TodoDTO> findByDepartmentAndUser(Department department, User user);
}
