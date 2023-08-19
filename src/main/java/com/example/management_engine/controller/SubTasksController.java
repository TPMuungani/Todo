package com.example.management_engine.controller;

import com.example.management_engine.domain.SubTasks;
import com.example.management_engine.service.SubTasksService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/subtasks")
public class SubTasksController {
    private final SubTasksService subTasksService;

    @PostMapping
    public ResponseEntity<SubTasks> addASubtask(@RequestBody SubTasks subTask){
        return new ResponseEntity<>(subTasksService.addSubtask(subTask), CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteASubTask(@PathVariable("id") Long id){
        subTasksService.deleteSubTask(id);
        return new ResponseEntity<>(OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<SubTasks> editASubtask(@PathVariable("id") Long id, @RequestBody SubTasks subTasks){
        return new ResponseEntity<>(subTasksService.editASubTaskById(id, subTasks), OK);
    }
}
