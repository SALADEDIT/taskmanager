package ru.salad.taskmanager.taskmanager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.salad.taskmanager.taskmanager.entity.Group;
import ru.salad.taskmanager.taskmanager.entity.Task;
import ru.salad.taskmanager.taskmanager.repositories.GroupRepository;
import ru.salad.taskmanager.taskmanager.repositories.TaskRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {


    private final TaskRepository taskRepository;

    private final GroupRepository groupRepository;


    public Optional<Task> getById(Integer id) {

        return Optional.ofNullable(taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Таска с ID " + id + " не найдена")));
    }


    public Task update(Integer id, Task request) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Таска не найдена"));

        task.setTitle(request.getTitle());
        task.setDescription(request.getDescription());
        task.setDeadLine(request.getDeadLine());
        task.setStatus(request.getStatus());


        if (request.getGroup() != null) {
            Group group = groupRepository.findById(request.getGroup().getId())
                    .orElseThrow(() -> new RuntimeException("Группа не найдена"));
            task.setGroup(group);
        }

        return taskRepository.save(task);
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }


    public void deleteTask(Integer id) {
        taskRepository.deleteById(id);
    }

    public Page<Task> getTasks(Integer companyId, Integer page, Integer size, String sortBy, String sortDirection) {
        Group group = groupRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Компания не найдена"));

        Sort sort = Sort.by(Sort.Direction.fromString(sortDirection), sortBy);
        Pageable pageable = PageRequest.of(page, size, sort);

        return taskRepository.findByGroup(group, pageable);
    }


}