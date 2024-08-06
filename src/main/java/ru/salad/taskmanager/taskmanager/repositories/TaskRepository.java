package ru.salad.taskmanager.taskmanager.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.salad.taskmanager.taskmanager.entity.Group;
import ru.salad.taskmanager.taskmanager.entity.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Page<Task> findByCompany(Group group, Pageable pageable);

}