package ru.salad.taskmanager.taskmanager.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.salad.taskmanager.taskmanager.entity.Task;

import java.util.List;

@Data
@NoArgsConstructor
public class GroupDto {
    private Integer id;
    private String name;
    private List<Task> tasks;

    public GroupDto(Integer id, String name) {
        this.id = id;
        this.name = name;
    }
}
