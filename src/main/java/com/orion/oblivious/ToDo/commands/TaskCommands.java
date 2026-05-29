package com.orion.oblivious.ToDo.commands;

import com.orion.oblivious.ToDo.models.Task;
import com.orion.oblivious.ToDo.services.TaskService;
import org.springframework.shell.core.command.annotation.Command;
import org.springframework.shell.core.command.annotation.Option;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class TaskCommands {
    private final TaskService taskService;

    public TaskCommands(TaskService taskService) {
        this.taskService = taskService;
    }

    // 1. ADD TASK COMMAND
    @Command(name = "add-task", description = "Add a brand new task to your ToDo list")
    public String addTask(
            @Option(longName = "name", description = "The title of your task") String name,
            @Option(longName = "description", description = "A detailed description") String description
    ) {
        this.taskService.addTask(name, description);
        return "[Success]: Task Added!";
    }

    // 2. GET ALL TASKS COMMAND
    @Command(name = "get-all-tasks", description = "Display all current tasks in a grid layout")
    public String getAllTasks() {
        List<Task> tasks = taskService.getAllTasks();

        if (tasks.isEmpty()) {
            return "[Info]: Your ToDo list is currently empty.";
        }

        StringBuilder sb = new StringBuilder();
        String headerFormat = "%-5s %-20s %-40s %-15s\n";
        sb.append(String.format(headerFormat, "ID", "Name", "Description", "Status"));
        sb.append("--------------------------------------------------------------------------------\n");

        String rowFormat = "%-5d %-20s %-40s %-15s\n";
        for (Task task : tasks) {
            sb.append(String.format(rowFormat,
                    task.getId(),
                    task.getName(),
                    task.getDescription(),
                    task.getStatus()
            ));
        }

        return sb.toString();
    }

    // 3. GET SINGLE TASK BY ID
    @Command(name = "get-task", description = "View details for a specific task using its ID")
    public String getTaskById(@Option(longName = "id", description = "The unique ID of the task") int id) {
        try {
            Task task = taskService.getTaskById(id);

            // Presentation Logic: Formatting a single task layout beautifully
            StringBuilder sb = new StringBuilder();
            sb.append("\n=== TASK DETAILS ===\n");
            sb.append(String.format("ID:          %d\n", task.getId()));
            sb.append(String.format("Name:        %s\n", task.getName()));
            sb.append(String.format("Description: %s\n", task.getDescription()));
            sb.append(String.format("Status:      %s\n", task.getStatus()));
            sb.append("====================\n");

            return sb.toString();
        } catch (IllegalArgumentException e) {
            return "[Error]: " + e.getMessage();
        }
    }

    // 4. UPDATE TASK STATUS
    @Command(name = "update-status", description = "Change the progress status of a task")
    public String updateStatus(
            @Option(longName = "id", description = "The unique ID of the task to update") int id,
            @Option(longName = "status", description = "The new status (not-started, in-progress, completed)") String status
    ) {
        try {
            taskService.updateStatus(id, status);
            return "[Success]: Status updated successfully to " + status.toUpperCase() + "!";
        } catch (IllegalArgumentException e) {
            return "[Error]: " + e.getMessage();
        }
    }

    // 5. DELETE TASK
    @Command(name = "delete-task", description = "Permanently remove a task from the list")
    public String deleteTaskById(@Option(longName = "id", description = "The unique ID of the task to erase") int id) {
        try {
            taskService.deleteTaskById(id);
            return "[Success]: Task #" + id + " has been permanently deleted.";
        } catch (IllegalArgumentException e) {
            return "[Error]: " + e.getMessage();
        }
    }
}