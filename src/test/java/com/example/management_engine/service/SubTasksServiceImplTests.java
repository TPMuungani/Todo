package com.example.management_engine.service;

import com.example.management_engine.domain.SubTasks;
import com.example.management_engine.enums.ProgressCheck;
import com.example.management_engine.repository.SubTasksRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class SubTasksServiceImplTests {
    @Mock
    private SubTasksRepository subTasksRepository;

    @InjectMocks
    private SubTasksServiceImpl subTasksService;

    //Requirements - Add a new subTask to the database
    @Test
    public void testAddSubTask(){
        //Arrange
        SubTasks subTask = new SubTasks();
        subTask.setId(1L);
        subTask.setName("SubTask1");
        subTask.setProgressCheck(ProgressCheck.OPEN);

        //Mock Behaviour
        Mockito.when(subTasksRepository.save(subTask)).thenReturn(subTask);

        SubTasks actualSubTasks = subTasksService.addSubtask(subTask);

        //Assert
        assertEquals(subTask, actualSubTasks);
        assertNotNull(subTask);
    }

    //Requirements - Update a subTask
    @Test
    public void testEditSubTask(){
        //Arrange
        SubTasks subTask = new SubTasks();
        subTask.setId(1L);
        subTask.setName("SubTask1");
        subTask.setProgressCheck(ProgressCheck.OPEN);

        //Mock Behaviour
        Mockito.when(subTasksRepository.findById(subTask.getId())).thenReturn(Optional.of(subTask));

        SubTasks actualSubTasks = subTasksService.editASubTaskById(subTask.getId(), subTask);

        //Assert
        assertEquals(subTask, actualSubTasks);
        assertNotNull(subTask);
    }
}
