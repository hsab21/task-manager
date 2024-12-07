package com.example.task_manager.infrastructure;

import com.example.task_manager.application.TaskService;
import com.example.task_manager.domain.Task;
import com.example.task_manager.exception.TaskNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Api(tags = "Gestion des tâches")
public class TaskController {
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/allTasks")
    @ApiOperation("Récupérer toutes les tâches")
    public ResponseEntity<List<Task>> getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/incomplete")
    @ApiOperation("Récupérer toutes les tâches incomplètes")
    public ResponseEntity<List<Task>> getIncompleteTasks() {
         List<Task> tasks = taskService.getIncompleteTasks();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    @ApiOperation("Récupérer une tâche par son ID")
    public Task getTaskById(@PathVariable String id) {
        return taskService.getTaskById(id).orElseThrow(() -> new TaskNotFoundException("Task not found"));
    }

    @PostMapping("/admin/NewTask")
    @ApiOperation("Créer une nouvelle tâche")
    public void createTask(@RequestBody Task task) {
        taskService.createTask(task);
    }

    @PatchMapping("/{id}/status")
    @ApiOperation("Mettre à jour le statut d'une tâche")
    public void updateTaskStatus(@PathVariable String id, @RequestParam boolean completed) {
        taskService.updateTaskStatus(id, completed);
    }
}
