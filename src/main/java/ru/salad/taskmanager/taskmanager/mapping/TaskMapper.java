package ru.salad.taskmanager.taskmanager.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.salad.taskmanager.taskmanager.dto.task.GetTaskDTO;
import ru.salad.taskmanager.taskmanager.dto.task.PostTaskDTO;
import ru.salad.taskmanager.taskmanager.entity.Task;

@Mapper(componentModel = "spring", uses = GroupMapper.class)
public interface TaskMapper {

    void updateTask(@MappingTarget Task task, PostTaskDTO postTaskDTO);

    GetTaskDTO taskToGetTaskDTO(Task task);

    Task getTaskDTOToTask(GetTaskDTO getTaskDTO);

    @Mapping(source = "groupId", target = "group.id")
    Task postTaskDTOToTask(PostTaskDTO postTaskDTO);

    @Mapping(source = "group.id", target = "groupId")
    PostTaskDTO taskToPostTaskDTO(Task task);
}
