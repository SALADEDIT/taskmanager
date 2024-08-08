package ru.salad.taskmanager.taskmanager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.salad.taskmanager.taskmanager.dto.GroupDTO;
import ru.salad.taskmanager.taskmanager.entity.Group;
import ru.salad.taskmanager.taskmanager.mapping.GroupMapper;
import ru.salad.taskmanager.taskmanager.repositories.GroupRepository;
import ru.salad.taskmanager.taskmanager.util.groupUtil.GroupErrorResponse;
import ru.salad.taskmanager.taskmanager.util.groupUtil.GroupNotFoundException;
import ru.salad.taskmanager.taskmanager.util.taskUtil.TaskNotFoundException;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final GroupMapper mapper;

    public ResponseEntity<GroupDTO> getById(Integer id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(GroupNotFoundException::new);
        GroupDTO groupDto = mapper.groupToGroupDTO(group);
        return new ResponseEntity<>(groupDto, HttpStatus.OK);
    }

    public ResponseEntity<GroupDTO> create(GroupDTO groupDTO) {

        Group group = mapper.groupDTOToGroup(groupDTO);

        Group created = groupRepository.save(group);
        GroupDTO createdDto = mapper.groupToGroupDTO(created);
        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);
    }

    public ResponseEntity<GroupDTO> update(Integer id, GroupDTO groupDTO) {
        Group group = groupRepository.findById(id)
                .orElseThrow(GroupNotFoundException::new);
        group.setName(groupDTO.getName());

        Group updated = groupRepository.save(group);
        GroupDTO updatedDto = mapper.groupToGroupDTO(updated);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    public void delete(Integer id) {
        groupRepository.deleteById(id);
    }
}
