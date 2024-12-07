package com.example.task_manager;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.example.task_manager.application.TaskService;
import com.example.task_manager.domain.Task;
import com.example.task_manager.infrastructure.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.*;

class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Initialiser les mocks
        task = new Task("1", "Test task","description ", false);  // Créer un exemple de tâche
    }

    @Test
    void testGetAllTasks() {
        List<Task> tasks = Arrays.asList(task);
        when(taskRepository.findAll()).thenReturn(tasks);  // Simuler le comportement du repository

        List<Task> result = taskService.getAllTasks();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Test task", result.get(0).getLabel());
    }

    @Test
    void testGetIncompleteTasks() {
        List<Task> incompleteTasks = Arrays.asList(task);
        when(taskRepository.findIncompleteTasks()).thenReturn(incompleteTasks);

        List<Task> result = taskService.getIncompleteTasks();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("Test task", result.get(0).getLabel());
        assertFalse(result.get(0).isCompleted());
    }

    @Test
    void testGetTaskById_Found() {
        when(taskRepository.findById("1")).thenReturn(Optional.of(task));

        Optional<Task> result = taskService.getTaskById("1");

        assertTrue(result.isPresent());
        assertEquals("1", result.get().getId());
    }

    @Test
    void testGetTaskById_NotFound() {
        when(taskRepository.findById("1")).thenReturn(Optional.empty());

        Optional<Task> result = taskService.getTaskById("1");

        assertFalse(result.isPresent());
    }

    @Test
    void testCreateTask() {
        taskService.createTask(task);

        verify(taskRepository, times(1)).save(task);  // Vérifier que save() est appelé une fois
    }

    @Test
    void testUpdateTaskStatus() {
        when(taskRepository.findById("1")).thenReturn(Optional.of(task));
        taskService.updateTaskStatus("1", true);

        assertTrue(task.isCompleted());  // La tâche devrait être marquée comme complète
        verify(taskRepository, times(1)).save(task);  // Vérifier que save() est appelé une fois
    }

    @Test
    void testUpdateTaskStatus_TaskNotFound() {
        when(taskRepository.findById("1")).thenReturn(Optional.empty());

        taskService.updateTaskStatus("1", true);

        verify(taskRepository, never()).save(any());  // Aucune sauvegarde ne doit être effectuée
    }
}
