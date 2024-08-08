package ru.salad.taskmanager.taskmanager.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.salad.taskmanager.taskmanager.dto.TaskDTO;
import ru.salad.taskmanager.taskmanager.entity.Task;

@Mapper(componentModel = "spring", uses = GroupMapper.class)
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(source = "status", target = "status")
    @Mapping(source = "group.id", target = "groupId")
    TaskDTO taskToTaskDTO(Task task);

    @Mapping(source = "status", target = "status")
    @Mapping(source = "groupId", target = "group.id")
    Task taskDTOToTask(TaskDTO taskDto);


}
