package ru.salad.taskmanager.taskmanager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.salad.taskmanager.taskmanager.dto.task.TaskDTO;
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


    public ResponseEntity<Task> getById(Integer id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Таска с ID " + id + " не найдена"));
        return new ResponseEntity<>(task, HttpStatus.OK);
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
    public ResponseEntity<TaskDTO> create(TaskDTO taskDTO) {

        Task task = mapper.taskDTOToTask(taskDTO);

        if (taskDTO.getGroupId() != null) {
            Optional<Group> group = groupRepository.findById(taskDTO.getGroupId());
            if (group.isPresent()) {
                task.setGroup(group.get());
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }

        Task savedTask = taskRepository.save(task);

        TaskDTO createdTaskDTO = mapper.taskToTaskDTO(savedTask);
        return new ResponseEntity<>(createdTaskDTO, HttpStatus.CREATED);
    }

    @Transactional
    public ResponseEntity<TaskDTO> update(Integer id, TaskDTO taskDTO) {
        Task task = taskRepository.findById(id)
                .orElseThrow(TaskNotFoundException::new);

        task.setTitle(taskDTO.getTitle());
        task.setDescription(taskDTO.getDescription());
        task.setDeadLine(taskDTO.getDeadLine());
        task.setStatus(taskDTO.getStatus());

        if (taskDTO.getGroupId() != null) {
            Optional<Group> group = groupRepository.findById(taskDTO.getGroupId());
            if (group.isPresent()) {
                task.setGroup(group.get());
            } else {
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }
        }

        Task updated = taskRepository.save(task);
        TaskDTO updatedDto = mapper.taskToTaskDTO(updated);
        return new ResponseEntity<>(updatedDto, HttpStatus.OK);
    }

    @Transactional
    public void delete(Integer id) {
        taskRepository.deleteById(id);
    }


}