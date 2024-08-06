package ru.salad.taskmanager.taskmanager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.salad.taskmanager.taskmanager.entity.Group;
import ru.salad.taskmanager.taskmanager.repositories.GroupRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;


    public Optional<Group> getCompanyById(Integer id) {
        return Optional.ofNullable(groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Компания с ID" + id + " не найдена")));
    }

    public Group createCompany(Group group) {
        return groupRepository.save(group);
    }

    public Group updateCompanyName(Integer companyId, String name) {
        Group group = groupRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Компания не найдена"));
        group.setName(name);
        return groupRepository.save(group);
    }

    public void deleteCompany(Integer id) {
        groupRepository.deleteById(id);
    }
}
