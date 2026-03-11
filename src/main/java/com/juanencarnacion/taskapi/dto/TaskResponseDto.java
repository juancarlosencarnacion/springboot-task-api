package com.juanencarnacion.taskapi.dto;

import java.time.LocalDateTime;

import com.juanencarnacion.taskapi.enums.TaskStatusEnum;

public record TaskResponseDto(
        Long taskId,
        String title,
        String description,
        TaskStatusEnum status,
        LocalDateTime createdAt,
        LocalDateTime updatedAt) {

}
