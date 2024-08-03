package ru.salad.taskmanager.taskmanager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.salad.taskmanager.taskmanager.entity.Status;
import ru.salad.taskmanager.taskmanager.entity.Task;
import ru.salad.taskmanager.taskmanager.repositories.CompanyRepository;
import ru.salad.taskmanager.taskmanager.repositories.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final CompanyRepository companyRepository;

    public Optional<Task> getTaskById(Integer id) {
        return taskRepository.findById(id);
    }

    public List<Task> getAllTaskByCompanyId(Integer companyId) {
        return taskRepository.findAllByCompanyId(companyId);
    }

    public Task deleteTaskById(Integer id) {
        return taskRepository.deleteTaskById(id);
    }

    public Task updateTaskStatus(Integer taskId, Status status) {
        Task task = taskRepository.findById(taskId)
                .orElseThrow(() -> new RuntimeException("Task not found"));
        task.setStatus(status);
        return taskRepository.save(task);
    }


}