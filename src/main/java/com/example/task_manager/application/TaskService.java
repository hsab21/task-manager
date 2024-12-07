package com.example.task_manager.application;

import com.example.task_manager.domain.Task;

import com.example.task_manager.infrastructure.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class TaskService {
    private final TaskRepository repository;

    public TaskService(TaskRepository repository) {
        this.repository = repository;
    }

    public List<Task> getAllTasks() {
        return repository.findAll();
    }

    public List<Task> getIncompleteTasks() {
        return repository.findIncompleteTasks();
    }

    public Optional<Task> getTaskById(String id) {
        return repository.findById(id);
    }

    public void createTask(Task task) {
        repository.save(task);
    }

    public void updateTaskStatus(String id, boolean completed) {
        repository.findById(id).ifPresent(task -> {
            task.setCompleted(completed);
            repository.save(task);
        });
    }
}
