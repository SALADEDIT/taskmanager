package ru.salad.taskmanager.taskmanager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.salad.taskmanager.taskmanager.dto.group.CreateGroupDTO;
import ru.salad.taskmanager.taskmanager.dto.group.GetGroupDTO;
import ru.salad.taskmanager.taskmanager.dto.group.UpdateGroupDTO;
import ru.salad.taskmanager.taskmanager.entity.Group;
import ru.salad.taskmanager.taskmanager.mapping.GroupMapper;
import ru.salad.taskmanager.taskmanager.repositories.GroupRepository;
import ru.salad.taskmanager.taskmanager.util.groupUtil.GroupNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GroupService {
    private final GroupRepository repository;
    private final GroupMapper mapper;

    public GetGroupDTO getById(Integer id) {
        return mapper.groupToGetGroupDTO(repository.findById(id)
                .orElseThrow(GroupNotFoundException::new));
    }

    @Transactional
    public GetGroupDTO create(CreateGroupDTO createGroupDTO) {
        return mapper.groupToGetGroupDTO(repository.save(mapper.createGroupDTOToGroup(createGroupDTO)));
    }

    @Transactional
    public GetGroupDTO update(Integer id, UpdateGroupDTO updateGroupDTO) {
        Group group = repository.findById(id)
                .orElseThrow(GroupNotFoundException::new);
        group.setName(updateGroupDTO.getName());
        return mapper.groupToGetGroupDTO(repository.save(group));
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
