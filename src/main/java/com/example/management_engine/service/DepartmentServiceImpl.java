package com.example.management_engine.service;

import com.example.management_engine.domain.Department;
import com.example.management_engine.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{
    private final DepartmentRepository departmentRepository;
    @Override
    public Department addDepartment(Department department) {
        return departmentRepository.save(department);
    }

    @Override
    public Department findByName(String name) {
        return departmentRepository.findByName(name);
    }

    @Override
    public Department findById(Long id) {
        return departmentRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Department> listAll() {
        return departmentRepository.findAll();
    }

    @Override
    public void deleteDepartmentById(Long id) {
        departmentRepository.deleteById(id);
    }
}
