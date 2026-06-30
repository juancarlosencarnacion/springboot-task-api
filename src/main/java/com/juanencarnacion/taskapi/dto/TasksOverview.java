package com.juanencarnacion.taskapi.dto;

public record TasksOverview(
    Long pendingTotal,
    Long inProgressTotal,
    Long completedTotal
) {

}
