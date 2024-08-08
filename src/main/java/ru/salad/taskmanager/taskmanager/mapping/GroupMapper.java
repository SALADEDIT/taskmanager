package ru.salad.taskmanager.taskmanager.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.salad.taskmanager.taskmanager.dto.group.GetGroupDTO;
import ru.salad.taskmanager.taskmanager.dto.group.PostGroupDTO;
import ru.salad.taskmanager.taskmanager.entity.Group;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    GetGroupDTO groupToGetGroupDTO(Group group);
    Group getGroupDTOToGroup(GetGroupDTO getGroupDTO);

    PostGroupDTO groupToPostGroupDTO(Group group);
    Group postGroupDTOToGroup(PostGroupDTO postGroupDTO);

}