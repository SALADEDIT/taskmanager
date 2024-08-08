package ru.salad.taskmanager.taskmanager.dto.group;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateGroupDTO {
    private Integer id;
    @Size(min = 2, max = 100, message = "Название не должно содержать меньше 2 и больше 100 букв")
    private String name;
}
