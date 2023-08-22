package com.example.management_engine.controller;

import com.example.management_engine.domain.Department;
import com.example.management_engine.service.DepartmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentControllerTests {
    @Mock
    private DepartmentService departmentService;
    @InjectMocks
    private DepartmentController departmentController;

    //Requirements - get all departments and return Https Status ok
    @Test
    public void testGetAllDepartments(){
        //Arrange
        Department department = new Department();
        department.setName("Statistics");

        //Mock Behaviour
        Mockito.when(departmentService.listAll()).thenReturn(List.of(department));

        ResponseEntity<List<Department>> actualResponse = departmentController.listAllDepartments();

        //Assert
        assertNotNull(actualResponse);
        assertEquals(actualResponse.getStatusCode(), HttpStatus.OK);
    }

    //Requirements - Add new department and return Https Status created
    @Test
    public void testAddDepartment(){
        //Arrange
        Department department = new Department();
        department.setName("Statistics");

        //Mock Behaviour
        Mockito.when(departmentService.addDepartment(department)).thenReturn(department);

        ResponseEntity<Department> actualResponse = departmentController.addDepartment(department);

        //Assert
        assertNotNull(actualResponse);
        assertEquals(actualResponse.getStatusCode(), HttpStatus.CREATED);
    }
    //Requirements - find a  department by id and return Https Status ok
    @Test
    public void testFindDepartmentById(){
        //Arrange
        Department department = new Department();
        department.setId(3L);
        department.setName("Statistics");

        //Mock Behaviour
        Mockito.when(departmentService.findById(department.getId())).thenReturn(department);

        ResponseEntity<Department> actualResponse = departmentController.findById(3L);

        //Assert
        assertNotNull(actualResponse);
        assertEquals(actualResponse.getStatusCode(), HttpStatus.OK);
    }

    //Requirements - find a  department by name and return Https Status ok
    @Test
    public void testFindDepartmentByName(){
        //Arrange
        Department department = new Department();
        department.setId(3L);
        department.setName("Statistics");

        //Mock Behaviour
        Mockito.when(departmentService.findByName(department.getName())).thenReturn(department);

        ResponseEntity<Department> actualResponse = departmentController.findBName("Statistics");

        //Assert
        assertNotNull(actualResponse);
        assertEquals(actualResponse.getStatusCode(), HttpStatus.OK);
    }
}
