package com.example.webapp.controller;

import com.example.webapp.dto.TaskDTO;
import com.example.webapp.model.User;
import com.example.webapp.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
@Tag(name = "Tasks", description = "Task management endpoints")
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    @Operation(summary = "Get all tasks for logged-in user")
    public ResponseEntity<List<TaskDTO.Response>> getAllTasks(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(taskService.getTasksByUser(user.getId()));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a task by ID")
    public ResponseEntity<TaskDTO.Response> getTask(@PathVariable Long id) {
        return ResponseEntity.ok(taskService.getTaskById(id));
    }

    @PostMapping
    @Operation(summary = "Create a new task")
    public ResponseEntity<TaskDTO.Response> createTask(
            @Valid @RequestBody TaskDTO.Request request,
            @AuthenticationPrincipal User user) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(taskService.createTask(request, user));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing task")
    public ResponseEntity<TaskDTO.Response> updateTask(
            @PathVariable Long id,
            @Valid @RequestBody TaskDTO.Request request) {
        return ResponseEntity.ok(taskService.updateTask(id, request));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a task")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
