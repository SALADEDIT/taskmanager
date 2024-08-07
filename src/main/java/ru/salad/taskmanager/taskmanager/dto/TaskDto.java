package ru.salad.taskmanager.taskmanager.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.salad.taskmanager.taskmanager.entity.Status;

import java.time.Instant;

@Data
@NoArgsConstructor
public class TaskDto {


    @Size(min = 2, max = 200, message = "Название не должно содержать меньше 2 и больше 200 букв")
    private String title;
    private String description;
    private Instant deadLine;
    @Pattern(regexp = "DONE|IN_PROGRESS|CLOSED", message = "Invalid status value")
    private Status status;
    private Integer group;

    public TaskDto(String title, String description, Instant deadLine, Status status, Integer group) {
        this.title = title;
        this.description = description;
        this.deadLine = deadLine;
        this.status = status;
        this.group = group;
    }
}