package com.orion.oblivious.ToDo.services;

import com.orion.oblivious.ToDo.models.Status;
import com.orion.oblivious.ToDo.models.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskServiceTest {

    private TaskService taskService;

    // This runs automatically before EVERY single test method below.
    // It guarantees a fresh, clean in-memory list for each test scenario.
    @BeforeEach
    void setUp() {
        taskService = new TaskService();
    }

    @Test
    void addTask_ShouldAppendTaskWithCorrectDefaults() {
        // Arrange & Act
        taskService.addTask("Test Task", "Testing our MVP logic");
        List<Task> tasks = taskService.getAllTasks();

        // Assert
        assertEquals(1, tasks.size(), "The task list size should be exactly 1");
        Task capturedTask = tasks.get(0);
        assertEquals(1, capturedTask.getId(), "The first task should dynamically receive an ID of 1");
        assertEquals("Test Task", java.util.Objects.requireNonNull(capturedTask.getName()));
        assertEquals(Status.NOT_STARTED, capturedTask.getStatus(), "New tasks must default to NOT_STARTED");
    }

    @Test
    void updateStatus_ShouldChangeStatus_WhenIdIsValid() {
        // Arrange
        taskService.addTask("Task 1", "Description");

        // Act
        taskService.updateStatus(1, "in-progress");
        Task updatedTask = taskService.getTaskById(1);

        // Assert
        assertEquals(Status.IN_PROGRESS, updatedTask.getStatus());
    }

    @Test
    void updateStatus_ShouldThrowException_WhenIdDoesNotExist() {
        // Assert execution triggers your customized IllegalArgumentException
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            taskService.updateStatus(999, "completed");
        });

        assertTrue(exception.getMessage().contains("does not exist"));
    }
}