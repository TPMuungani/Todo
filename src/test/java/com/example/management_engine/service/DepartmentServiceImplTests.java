package com.example.management_engine.service;

import com.example.management_engine.domain.Department;
import com.example.management_engine.repository.DepartmentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class DepartmentServiceImplTests {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    //Requirements - Add a new Department
    @Test
    public void testAddDepartment(){
        //Arrange
        Department department = new Department();
        department.setName("Finance");
        department.setId(1L);

        //Mock Behaviour
        Mockito.when(departmentRepository.save(department)).thenReturn(department);

        Department departmentActual = departmentService.addDepartment(department);

        //Assert
        assertEquals(departmentActual, department);
        assertNotNull(departmentActual);
    }

    //Requirements - Find Department by name
    @Test
    public void testFindByName(){
        //Arrange
        Department department = new Department();
        department.setName("Finance");
        department.setId(1L);

        //Mock Behaviour
        Mockito.when(departmentRepository.findByName(department.getName())).thenReturn(department);

        Department departmentActual = departmentService.findByName("Finance");

        //Assert
        assertEquals(departmentActual, department);
        assertNotNull(departmentActual);
    }

    //Requirements - Find Department by id
    @Test
    public void testFindById(){
        //Arrange
        Department department = new Department();
        department.setName("Finance");
        department.setId(1L);

        //Mock Behaviour
        Mockito.when(departmentRepository.findById(department.getId())).thenReturn(Optional.of(department));

        Department departmentActual = departmentService.findById(department.getId());

        //Assert
        assertEquals(departmentActual, department);
        assertNotNull(departmentActual);
    }
    //Requirements - Find All Departments
    @Test
    public void testFindAll(){
        //Arrange
        Department department = new Department();
        department.setName("Finance");
        department.setId(1L);

        //Mock Behaviour
        Mockito.when(departmentRepository.findAll()).thenReturn(List.of(department));

        List<Department> departmentActual = departmentService.listAll();

        //Assert
        assertEquals(departmentActual, List.of(department));
        assertNotNull(departmentActual);
    }

//    //Requirements - Delete a  Department by Id
//    @Test
//    public void testDeleteById(){
//        //Arrange
//        Department department = new Department();
//        department.setName("Finance");
//        department.setId(1L);
//
//        //Mock Behaviour
//        Mockito.when(departmentRepository.deleteById(department.getId())).thenReturn(null);
//
//         departmentActual = departmentService.listAll();
//
//        //Assert
//        assertEquals(departmentActual, List.of(department));
//        assertNotNull(departmentActual);
//    }
}
