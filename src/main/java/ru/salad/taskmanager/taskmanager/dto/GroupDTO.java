package ru.salad.taskmanager.taskmanager.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.salad.taskmanager.taskmanager.entity.Task;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GroupDTO {

    private Integer id;
    @Size(min = 2, max = 100, message = "Название не должно содержать меньше 2 и больше 100 букв")
    private String name;
    private List<Task> tasks;

}
