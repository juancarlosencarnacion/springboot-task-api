package com.juanencarnacion.taskapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.juanencarnacion.taskapi.model.TaskEntity;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

}
