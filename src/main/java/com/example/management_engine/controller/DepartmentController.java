package com.example.management_engine.controller;

import com.example.management_engine.domain.Department;
import com.example.management_engine.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/department")
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> listAllDepartments(){
        return new ResponseEntity<>(departmentService.listAll(), OK);
    }
    @PostMapping
    public ResponseEntity<Department> addDepartment(@RequestBody Department department){
        return new ResponseEntity<>(departmentService.addDepartment(department), CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable("id") Long id){
        departmentService.deleteDepartmentById(id);
        return new ResponseEntity<>(OK);
    }
    @GetMapping("/department name")
    public ResponseEntity<Department> findBName(@RequestParam String name){
        return new ResponseEntity<>(departmentService.findByName(name), OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Department> findById(@PathVariable("id") Long id){
        return new ResponseEntity<>(departmentService.findById(id), OK);
    }
}
