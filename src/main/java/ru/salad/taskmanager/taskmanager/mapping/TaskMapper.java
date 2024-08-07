package ru.salad.taskmanager.taskmanager.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.salad.taskmanager.taskmanager.dto.TaskDto;
import ru.salad.taskmanager.taskmanager.entity.Task;

@Mapper
public interface TaskMapper {

    TaskMapper INSTANCE = Mappers.getMapper(TaskMapper.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "title", target = "title")
    @Mapping(source = "description", target = "description")
    @Mapping(source = "deadLine", target = "deadLine")
    @Mapping(source = "status", target = "status")
    @Mapping(source = "group", target = "group")
    TaskDto taskToTaskDto(Task task);
}
