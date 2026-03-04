package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // GET all
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // POST - create
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    // GET by id
    public Optional<Task> getTaskById(Long id) {
        return taskRepository.findById(id);
    }

    // PUT - full update (all fields replaced)
    public Optional<Task> updateTask(Long id, Task taskDetails) {
        return taskRepository.findById(id)
                .map(task -> {
                    task.setTitle(taskDetails.getTitle());
                    task.setDescription(taskDetails.getDescription());
                    task.setCompleted(taskDetails.getCompleted());
                    return taskRepository.save(task);
                });
    }

    // PATCH - partial update (only non-null fields updated)
    public Optional<Task> patchTask(Long id, Task taskDetails) {
        return taskRepository.findById(id)
                .map(task -> {
                    if (taskDetails.getTitle() != null) {
                        task.setTitle(taskDetails.getTitle());
                    }
                    if (taskDetails.getDescription() != null) {
                        task.setDescription(taskDetails.getDescription());
                    }
                    if (taskDetails.getCompleted() != null) {
                        task.setCompleted(taskDetails.getCompleted());
                    }
                    return taskRepository.save(task);
                });
    }

    // DELETE
    public boolean deleteTask(Long id) {
        return taskRepository.findById(id)
                .map(task -> {
                    taskRepository.delete(task);
                    return true;
                })
                .orElse(false);
    }
}