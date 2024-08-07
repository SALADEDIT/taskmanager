package ru.salad.taskmanager.taskmanager.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.salad.taskmanager.taskmanager.dto.GroupDto;
import ru.salad.taskmanager.taskmanager.entity.Group;

@Mapper
public interface GroupMapping {

    GroupMapping INSTANCE = Mappers.getMapper(GroupMapping.class);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "name", target = "name")
    GroupDto groupToGroupDto(Group group);
}