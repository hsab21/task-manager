package com.example.task_manager.exception;

public class TaskNotFoundException extends RuntimeException {
    public TaskNotFoundException(String taskId) {
        super("Task with ID " + taskId + " not found");
    }
}
