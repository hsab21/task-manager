package com.example.task_manager.infrastructure;

import com.example.task_manager.domain.Task;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryTaskRepository implements TaskRepository {
    private final List<Task> tasks = new ArrayList<>();

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(tasks);
    }

    @Override
    public List<Task> findIncompleteTasks() {
        return tasks.stream().filter(task -> !task.isCompleted()).toList();
    }

    @Override
    public Optional<Task> findById(String id) {
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst();
    }

    @Override
    public void save(Task task) {
        tasks.removeIf(existingTask -> existingTask.getId().equals(task.getId()));
        tasks.add(task);
    }

    @Override
    public void delete(String id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }
}
