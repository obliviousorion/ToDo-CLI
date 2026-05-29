package com.orion.oblivious.ToDo.services;


import com.orion.oblivious.ToDo.models.Status;
import com.orion.oblivious.ToDo.models.Task;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class TaskService {
    private final ArrayList<Task> tasks = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    public List<Task> getAllTasks() {
        return tasks;
    }
    public void addTask(String name, String description) {
        tasks.add(
                new Task(
                        idCounter.incrementAndGet(), name, description, Status.NOT_STARTED
                )
        );
    }

    // update task status
    public void updateStatus(int id, String status) {
        Status newStatus = Status.fromString(status);
        for (Task task : tasks) {
            if (task.getId() == id) {
                task.setStatus(newStatus);
                return;
            }
        }
        throw new IllegalArgumentException("Task with id " + id + " does not exist");
    }

    // display task or get task by id
    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
            return task;
            }
        }
        throw new IllegalArgumentException("Task with id " + id + " does not exist");
    }

    // delete task
    public void deleteTaskById(int id) {
        boolean isRemoved = tasks.removeIf(task -> task.getId() == id);
        if (!isRemoved) {
            throw new IllegalArgumentException("Task with id " + id + " does not exist");
        }
    }
}
