package com.juanencarnacion.taskapi.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.juanencarnacion.taskapi.dto.TaskRequestDto;
import com.juanencarnacion.taskapi.dto.TaskResponseDto;
import com.juanencarnacion.taskapi.enums.TaskStatusEnum;
import com.juanencarnacion.taskapi.model.TaskEntity;
import com.juanencarnacion.taskapi.repository.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskResponseDto createTask(TaskRequestDto request) {
        if (request.title().isBlank() || request.description().isBlank()) {
            throw new RuntimeException("All the fields are required");
        }

        TaskEntity newTask = TaskEntity.builder()
                .title(request.title())
                .description(request.description())
                .status(request.status())
                .createdAt(LocalDateTime.now())
                .build();

        TaskEntity savedTask = taskRepository.save(newTask);

        return new TaskResponseDto(
                savedTask.getTaskId(),
                savedTask.getTitle(),
                savedTask.getDescription(),
                savedTask.getStatus(),
                savedTask.getCreatedAt(),
                savedTask.getUpdatedAt());

    }

    public List<TaskResponseDto> getTasks() {
        return taskRepository.findAll()
                .stream()
                .sorted(Comparator.comparingLong(TaskEntity::getTaskId))
                .map(task -> new TaskResponseDto(
                        task.getTaskId(),
                        task.getTitle(),
                        task.getDescription(),
                        task.getStatus(),
                        task.getCreatedAt(),
                        task.getUpdatedAt()))
                .toList();
    }

    public TaskResponseDto getTaskById(Long taskId) {
        TaskEntity taskFound = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        return new TaskResponseDto(
                taskFound.getTaskId(),
                taskFound.getTitle(),
                taskFound.getDescription(),
                taskFound.getStatus(),
                taskFound.getCreatedAt(),
                taskFound.getUpdatedAt());

    }

    public TaskResponseDto updateTask(Long taskId, TaskRequestDto request) {
        TaskEntity taskFound = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        if (request.title() != null && !request.title().isBlank()) {
            taskFound.setTitle(request.title());
        }
        if (request.description() != null && !request.description().isBlank()) {
            taskFound.setDescription(request.description());
        }
        if (request.status() != null) {
            TaskStatusEnum status = request.status();
            taskFound.setStatus(status);
        }

        taskFound.setUpdatedAt(LocalDateTime.now());

        taskRepository.save(taskFound);

        return new TaskResponseDto(
                taskFound.getTaskId(),
                taskFound.getTitle(),
                taskFound.getDescription(),
                taskFound.getStatus(),
                taskFound.getCreatedAt(),
                taskFound.getUpdatedAt());
    }

    public void deleteTask(Long taskId) {
        TaskEntity taskFound = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));

        taskRepository.delete(taskFound);
    }
}
