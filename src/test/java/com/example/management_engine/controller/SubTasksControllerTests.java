package com.example.management_engine.controller;

import com.example.management_engine.domain.Department;
import com.example.management_engine.domain.SubTasks;
import com.example.management_engine.enums.ProgressCheck;
import com.example.management_engine.service.SubTasksService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class SubTasksControllerTests {
    @Mock
    private SubTasksService subTasksService;
    @InjectMocks
    private SubTasksController subTasksController;

    //Requirements - add a new subtask and return Https Status created
    @Test
    public void testAddSubTask(){
        //Arrange
        SubTasks subTasks = new SubTasks();
        subTasks.setId(3L);
        subTasks.setName("Statistics");
        subTasks.setProgressCheck(ProgressCheck.OPEN);

        //Mock Behaviour
        Mockito.when(subTasksService.addSubtask(subTasks)).thenReturn(subTasks);

        ResponseEntity<SubTasks> actualResponse = subTasksController.addASubtask(subTasks);

        //Assert
        assertNotNull(actualResponse);
        assertEquals(actualResponse.getStatusCode(), HttpStatus.CREATED);
    }

    //Requirements - edit a  subtask and return Https Status ok
    @Test
    public void testEditSubTask(){
        //Arrange
        SubTasks subTasks = new SubTasks();
        subTasks.setId(3L);
        subTasks.setName("Statistics");
        subTasks.setProgressCheck(ProgressCheck.OPEN);

        //Mock Behaviour
        Mockito.when(subTasksService.editASubTaskById(1L, subTasks)).thenReturn(subTasks);

        ResponseEntity<SubTasks> actualResponse = subTasksController.editASubtask(2L,subTasks);

        //Assert
        assertNotNull(actualResponse);
        assertEquals(actualResponse.getStatusCode(), HttpStatus.OK);
    }

}
