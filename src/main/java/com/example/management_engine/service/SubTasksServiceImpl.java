package com.example.management_engine.service;

import com.example.management_engine.domain.SubTasks;
import com.example.management_engine.repository.SubTasksRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.example.management_engine.enums.ProgressCheck.OPEN;

@Service
@RequiredArgsConstructor
public class SubTasksServiceImpl implements SubTasksService{
    private final SubTasksRepository subTasksRepository;
    @Override
    public SubTasks addSubtask(SubTasks subTasks) {
        SubTasks subTasks1 = new SubTasks();
        subTasks1.setName(subTasks.getName());
        subTasks1.setProgressCheck(OPEN);
        return subTasksRepository.save(subTasks1);
    }

    @Override
    public void deleteSubTask(Long id) {
        subTasksRepository.deleteById(id);
    }

    @Override
    public SubTasks editASubTaskById(Long id, SubTasks subTasks) {
        Optional<SubTasks> existingSubTask = subTasksRepository.findById(id);
        existingSubTask.get().setName(subTasks.getName());
        existingSubTask.get().setProgressCheck(subTasks.getProgressCheck());
        subTasksRepository.save(existingSubTask.get());
        return existingSubTask.get();
    }
}
