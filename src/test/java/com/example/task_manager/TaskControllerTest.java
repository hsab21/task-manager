package com.example.task_manager;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.OK;

import com.example.task_manager.application.TaskService;
import com.example.task_manager.domain.Task;
import com.example.task_manager.infrastructure.TaskController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TaskControllerTest {

    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetAllTasks() {
        List<Task> tasks = Arrays.asList(new Task("1", "Test task","description ", false), new Task("2", "Test task","description ", false));
        when(taskService.getAllTasks()).thenReturn(tasks);

        ResponseEntity<List<Task>> response = taskController.getAllTasks();
        assertEquals(OK , response.getStatusCode());
        assertEquals(tasks, response.getBody());
    }

    @Test
    public void testGetIncompleteTasks() {
        List<Task> tasks = Arrays.asList(new Task("1", "Test task","description ", false), new Task("2", "Test task","description ", false));
        when(taskService.getIncompleteTasks()).thenReturn(tasks);

        ResponseEntity<List<Task>> response = taskController.getIncompleteTasks();
        assertEquals(OK , response.getStatusCode());
        assertEquals(tasks, response.getBody());
    }

    @Test
    public void testGetTaskById() {
        Task  task = new Task("1", "Test task","description ", false);
        when(taskService.getTaskById("1")).thenReturn(Optional.of(task));

        Task result = taskController.getTaskById("1");
        assertEquals(task, result);
    }

    @Test
    public void testCreateTask() {
        Task task = new Task("1", "Test task","description ", false);
        doNothing().when(taskService).createTask(task);

        taskController.createTask(task);
        verify(taskService, times(1)).createTask(task);
    }

    @Test
    public void testUpdateTaskStatus() {
        doNothing().when(taskService).updateTaskStatus("1", true);

        taskController.updateTaskStatus("1", true);
        verify(taskService, times(1)).updateTaskStatus("1", true);
    }
}
