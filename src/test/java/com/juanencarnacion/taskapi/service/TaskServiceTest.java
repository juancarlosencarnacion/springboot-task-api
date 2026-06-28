package com.juanencarnacion.taskapi.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.juanencarnacion.taskapi.dto.TaskResponseDto;
import com.juanencarnacion.taskapi.enums.TaskStatusEnum;
import com.juanencarnacion.taskapi.model.TaskEntity;
import com.juanencarnacion.taskapi.repository.TaskRepository;

@ExtendWith(MockitoExtension.class) // Inicializa las anotaciones de Mockito
public class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository; // Crea un simulador falso de tu repositorio

    @InjectMocks
    TaskService taskService; // Inyecta el repositorio simulado dentro de tu servicio

    @Test
    @DisplayName("Deberia retornar una tarea correctamente cuando el ID existe")
    void getTaskById_WhenIdExists_ShouldReturnTask() {

        // 1. GIVEN (Preparación de los datos de prueba)
        Long taskId = 1L;
        TaskEntity mockEntity = TaskEntity.builder()
                .taskId(taskId)
                .title("Aprender tests")
                .description("Practicar JUnit y Mockito")
                .status(TaskStatusEnum.PENDING)
                .createdAt(LocalDateTime.now())
                .build();

        // Le decimos al mock: "Cuando llamen a findById(1), devuelve este mockEntity"
        when(taskRepository.findById(taskId)).thenReturn(Optional.of(mockEntity));

        // 2. WHEN (Ejecución de la acción que queremos probar)
        TaskResponseDto response = taskService.getTaskById(taskId);

        // 3. THEN (Verificaciones de que todo salió como esperábamos)
        assertNotNull(response);
        assertEquals(taskId, response.taskId());
        assertEquals("Aprender tests", response.title());
        assertEquals(TaskStatusEnum.PENDING, response.status());

        // Opcional: Verifica que el repositorio realmente fue llamado exactamente 1 vez
        verify(taskRepository).findById(taskId);
    }

    @Test
    @DisplayName("Deberia lanzar una excepcion cuando el ID de la tarea no existe")
    void getTaskById_WhenIdDoesNotExist_ShouldThrowException() {
        // 1. GIVEN
        Long taskId = 99L;
        // Simulamos que la base de datos devuelve un vacío (Optional.empty())
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // 2. WHEN & THEN (Ejecutamos y verificamos que lance RuntimeException)
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            taskService.getTaskById(taskId);
        });

        // Verificamos que el mensaje de error sea el esperado
        assertEquals("Task not found", exception.getMessage());

    }

}
