package com.example.management_engine.service;

import com.example.management_engine.data.DepartmentDTO;
import com.example.management_engine.domain.Department;

import java.util.List;

public interface DepartmentService {
    Department addDepartment(Department department);
    Department findByName(String name);
    Department findById(Long id);
    List<Department> listAll();
    void deleteDepartmentById(Long id);
}
