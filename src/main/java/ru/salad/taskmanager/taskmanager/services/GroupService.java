package ru.salad.taskmanager.taskmanager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.salad.taskmanager.taskmanager.dto.group.GetGroupDTO;
import ru.salad.taskmanager.taskmanager.dto.group.PostGroupDTO;
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
    public GetGroupDTO create(PostGroupDTO postGroupDTO) {
        // Такая вложенность норм с позиции читаемости?
        return mapper.groupToGetGroupDTO(repository.save(mapper.postGroupDTOToGroup(postGroupDTO)));
    }

    @Transactional
    public GetGroupDTO update(Integer id, PostGroupDTO postGroupDTO) {
        Group group = repository.findById(id)
                .orElseThrow(GroupNotFoundException::new);

        mapper.updateGroup(group, postGroupDTO);
        return mapper.groupToGetGroupDTO(repository.save(group));
    }

    @Transactional
    public void delete(Integer id) {
        repository.deleteById(id);
    }
}
