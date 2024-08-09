package ru.salad.taskmanager.taskmanager.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.salad.taskmanager.taskmanager.dto.task.GetTaskDTO;
import ru.salad.taskmanager.taskmanager.dto.task.PostTaskDTO;
import ru.salad.taskmanager.taskmanager.entity.Group;
import ru.salad.taskmanager.taskmanager.entity.Task;

@Mapper(componentModel = "spring", uses = GroupMapper.class) // Убедитесь, что GroupMapper существует и используется корректно
public interface TaskMapper {

    // Метод для обновления существующего Task объектом PostTaskDTO
    void updateTask(@MappingTarget Task task, PostTaskDTO postTaskDTO);

    // Метод для маппинга Task на GetTaskDTO
    GetTaskDTO taskToGetTaskDTO(Task task);

    // Метод для маппинга GetTaskDTO на Task
    Task getTaskDTOToTask(GetTaskDTO getTaskDTO);

    // Метод для маппинга PostTaskDTO на Task с указанием соответствия полей
    @Mapping(source = "groupId", target = "group.id")
    Task postTaskDTOToTask(PostTaskDTO postTaskDTO);

    // Метод для маппинга Task на PostTaskDTO с указанием соответствия полей
    @Mapping(source = "group.id", target = "groupId")
    PostTaskDTO taskToPostTaskDTO(Task task);
}
