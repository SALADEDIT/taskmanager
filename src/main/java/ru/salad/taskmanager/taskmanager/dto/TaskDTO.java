package ru.salad.taskmanager.taskmanager.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.salad.taskmanager.taskmanager.entity.Status;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskDTO {


    @Size(min = 2, max = 100, message = "Заголовок должен содержать от 2 до 100 символов")
    private String title;
    private String description;
    private Instant deadLine;
//    @Pattern(regexp = "DONE|IN_PROGRESS|CLOSED", message = "Invalid status value")
    private Status status;
    private Integer groupId;
}