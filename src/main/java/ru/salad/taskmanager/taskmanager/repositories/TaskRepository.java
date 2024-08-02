package ru.salad.taskmanager.taskmanager.repositories;

import org.springframework.data.repository.Repository;
import ru.salad.taskmanager.taskmanager.models.Task;

public interface TaskRepository extends Repository<Task, Integer> {
    Task findById(Integer groupId);
}