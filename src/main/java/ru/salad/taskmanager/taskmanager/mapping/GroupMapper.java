package ru.salad.taskmanager.taskmanager.mapping;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import ru.salad.taskmanager.taskmanager.dto.group.GetGroupDTO;
import ru.salad.taskmanager.taskmanager.dto.group.PostGroupDTO;
import ru.salad.taskmanager.taskmanager.dto.task.GetTaskDTO;
import ru.salad.taskmanager.taskmanager.entity.Group;
import ru.salad.taskmanager.taskmanager.entity.Task;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

    @Mapping(target = "id", ignore = true)
    void updateGroup(@MappingTarget Group group, PostGroupDTO postGroupDTO);


    GetGroupDTO groupToGetGroupDTO(Group group);

    Group getGroupDTOToGroup(GetGroupDTO getGroupDTO);

    PostGroupDTO groupToPostGroupDTO(Group group);

    Group postGroupDTOToGroup(PostGroupDTO postGroupDTO);

}