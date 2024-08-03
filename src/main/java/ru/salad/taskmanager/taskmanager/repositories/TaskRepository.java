package ru.salad.taskmanager.taskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.salad.taskmanager.taskmanager.entity.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {


    Optional<Task> findById(Integer id);

    List<Task> findAllByCompanyId(Integer companyId);

    Task deleteTaskById(Integer id);

    //    Task updateTaskByIdAndStatus(Integer id, Status status);

}