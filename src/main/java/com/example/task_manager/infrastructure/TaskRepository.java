package com.example.task_manager.infrastructure;

import com.example.task_manager.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository {
    List<Task> findAll();
    List<Task> findIncompleteTasks();
    Optional<Task> findById(String id);
    void save(Task task);
    void delete(String id);
}
