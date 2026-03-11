package com.juanencarnacion.taskapi.dto;

import com.juanencarnacion.taskapi.enums.TaskStatusEnum;

public record TaskRequestDto(
        String title,
        String description,
        TaskStatusEnum status) {

}
