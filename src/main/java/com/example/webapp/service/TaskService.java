package com.example.webapp.service;

import com.example.webapp.dto.TaskDTO;
import com.example.webapp.model.Task;
import com.example.webapp.model.User;
import com.example.webapp.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<TaskDTO.Response> getTasksByUser(Long userId) {
        return taskRepository.findByUserId(userId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public TaskDTO.Response getTaskById(Long taskId) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found: " + taskId));
        return toResponse(task);
    }

    public TaskDTO.Response createTask(TaskDTO.Request request, User user) {
        Task task = Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus() != null ? request.getStatus() : Task.TaskStatus.TODO)
                .priority(request.getPriority() != null ? request.getPriority() : Task.Priority.MEDIUM)
                .dueDate(request.getDueDate())
                .user(user)
                .build();
        return toResponse(taskRepository.save(task));
    }

    public TaskDTO.Response updateTask(Long taskId, TaskDTO.Request request) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found: " + taskId));
        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        if (request.getStatus() != null) task.setStatus(request.getStatus());
        if (request.getPriority() != null) task.setPriority(request.getPriority());
        task.setDueDate(request.getDueDate());
        return toResponse(taskRepository.save(task));
    }

    public void deleteTask(Long taskId) {
        taskRepository.deleteById(taskId);
    }

    private TaskDTO.Response toResponse(Task task) {
        return TaskDTO.Response.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .priority(task.getPriority())
                .dueDate(task.getDueDate())
                .createdAt(task.getCreatedAt())
                .updatedAt(task.getUpdatedAt())
                .build();
    }
}
