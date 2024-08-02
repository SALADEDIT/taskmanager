package ru.salad.taskmanager.taskmanager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.salad.taskmanager.taskmanager.models.Task;
import ru.salad.taskmanager.taskmanager.repositories.CompanyRepository;
import ru.salad.taskmanager.taskmanager.repositories.TaskRepository;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final CompanyRepository companyRepository;

    public Task getTaskById(int id) {
        return taskRepository.findById(id);
    }
}