package ru.salad.taskmanager.taskmanager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.salad.taskmanager.taskmanager.dto.task.GetTaskDTO;
import ru.salad.taskmanager.taskmanager.dto.task.PostTaskDTO;
import ru.salad.taskmanager.taskmanager.entity.Group;
import ru.salad.taskmanager.taskmanager.entity.Task;
import ru.salad.taskmanager.taskmanager.mapping.TaskMapper;
import ru.salad.taskmanager.taskmanager.repositories.GroupRepository;
import ru.salad.taskmanager.taskmanager.repositories.TaskRepository;
import ru.salad.taskmanager.taskmanager.util.groupUtil.GroupNotFoundException;
import ru.salad.taskmanager.taskmanager.util.taskUtil.TaskNotFoundException;

import java.time.Instant;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {


    private final TaskRepository taskRepository;
    private final GroupRepository groupRepository;

    private final TaskMapper mapper;


    public Task getById(Integer id) {
        return taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);
    }

    public Page<Task> getPages(Integer companyId, Integer page, Integer size, String sortBy,
                               String filter, String sortDirection, Instant startDate, Instant endDate) {
        Group group = groupRepository.findById(companyId)
                .orElseThrow(GroupNotFoundException::new);

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return switch (sortBy.toLowerCase()) {
            case "title" -> taskRepository.findByGroupAndTitleContainingIgnoreCase(group, filter, pageable);
            case "status" -> taskRepository.findByGroupAndStatusContainingIgnoreCase(group, filter, pageable);
            case "deadline" -> taskRepository.findByGroupAndDeadlineBetween(group, startDate, endDate, pageable);
            default -> taskRepository.findByGroup(group, pageable);
        };

    }
@Transactional
    public GetTaskDTO create(PostTaskDTO postTaskDTO) {

        Task task = mapper.postTaskDTOToTask(postTaskDTO);

        if (postTaskDTO.getGroupId() != null) {
            Group group = groupRepository.findById(postTaskDTO.getGroupId())
                    .orElseThrow(GroupNotFoundException::new);
            task.setGroup(group);
        }

        return mapper.taskToGetTaskDTO(taskRepository.save(task));
    }

    @Transactional
    public GetTaskDTO update(Integer id, PostTaskDTO postTaskDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);

        if (postTaskDTO.getGroupId() != null) {
            Group group = groupRepository.findById(postTaskDTO.getGroupId())
                    .orElseThrow(GroupNotFoundException::new);
            task.setGroup(group);
        }

        mapper.updateTask(task, postTaskDTO);

        return mapper.taskToGetTaskDTO(taskRepository.save(task));
    }

    @Transactional
    public void delete(Integer id) {
        taskRepository.deleteById(id);
    }


}