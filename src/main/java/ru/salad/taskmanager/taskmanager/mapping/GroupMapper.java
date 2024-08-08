package ru.salad.taskmanager.taskmanager.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.salad.taskmanager.taskmanager.dto.group.CreateGroupDTO;
import ru.salad.taskmanager.taskmanager.dto.group.GetGroupDTO;
import ru.salad.taskmanager.taskmanager.dto.group.UpdateGroupDTO;
import ru.salad.taskmanager.taskmanager.entity.Group;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    GetGroupDTO groupToGetGroupDTO(Group group);
    Group getGroupDTOToGroup(GetGroupDTO getGroupDTO);

    CreateGroupDTO groupToCreateGroupDTO(Group group);
    Group createGroupDTOToGroup(CreateGroupDTO createGroupDTO);

    UpdateGroupDTO groupToUpdateGroupDTO(Group group);
    Group updateGroupDTOToGroup(UpdateGroupDTO updateGroupDTO);
}