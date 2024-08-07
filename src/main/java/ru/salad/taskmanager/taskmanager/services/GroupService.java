package ru.salad.taskmanager.taskmanager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.salad.taskmanager.taskmanager.dto.GroupDto;
import ru.salad.taskmanager.taskmanager.entity.Group;
import ru.salad.taskmanager.taskmanager.entity.Task;
import ru.salad.taskmanager.taskmanager.repositories.GroupRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;


    public Optional<Group> getById(Integer id) {
        return Optional.ofNullable(groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Компания с ID" + id + " не найдена")));
    }

    public Group create(Group group) {
        return groupRepository.save(group);
    }

    public Group update(Integer id, Group request) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Таска не найдена"));

        group.setName(request.getName());

        return groupRepository.save(group);
    }

    public void delete(Integer id) {
        groupRepository.deleteById(id);
    }
}
