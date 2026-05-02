package com.example.webapp.dto;

import com.example.webapp.model.Task;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import java.time.LocalDateTime;

public class TaskDTO {

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        @NotBlank(message = "Title is required")
        private String title;
        private String description;
        private Task.TaskStatus status;
        private Task.Priority priority;
        private LocalDateTime dueDate;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        private Long id;
        private String title;
        private String description;
        private Task.TaskStatus status;
        private Task.Priority priority;
        private LocalDateTime dueDate;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
    }
}
