package com.example.management_engine.service;

import com.example.management_engine.domain.SubTasks;

public interface SubTasksService {
    SubTasks addSubtask(SubTasks subTasks);
    void deleteSubTask(Long id);

    SubTasks editASubTaskById(Long id, SubTasks subTasks);
}
