package ru.salad.taskmanager.taskmanager.dto.task;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.salad.taskmanager.taskmanager.entity.Status;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTaskDTO {

    private String title;
    private String description;
    private Instant deadLine;
    private Status status;
}

