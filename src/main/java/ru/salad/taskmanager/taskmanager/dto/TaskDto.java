package ru.salad.taskmanager.taskmanager.dto;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import ru.salad.taskmanager.taskmanager.entity.Status;

import java.time.Instant;

@Data
@AllArgsConstructor
@RequiredArgsConstructor

public class TaskDto {

    @NotEmpty(message = "Заголовок не должен быть пустым")
    private String title;

    private String description;

    private Instant deadLine;

    @Enumerated(EnumType.STRING)
    private Status status;
}
