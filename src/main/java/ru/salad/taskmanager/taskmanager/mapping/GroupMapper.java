package ru.salad.taskmanager.taskmanager.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.salad.taskmanager.taskmanager.dto.GroupDTO;
import ru.salad.taskmanager.taskmanager.entity.Group;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    GroupDTO groupToGroupDTO(Group group);

    Group groupDTOToGroup(GroupDTO groupDTO);
}