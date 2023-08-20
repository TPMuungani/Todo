package com.example.management_engine.repository;

import com.example.management_engine.domain.SubTasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubTasksRepository extends JpaRepository<SubTasks, Long> {
}
