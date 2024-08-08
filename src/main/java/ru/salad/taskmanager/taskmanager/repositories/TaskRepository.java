package ru.salad.taskmanager.taskmanager.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.salad.taskmanager.taskmanager.entity.Group;
import ru.salad.taskmanager.taskmanager.entity.Task;

import java.time.Instant;

@Repository
public interface TaskRepository extends JpaRepository<Task, Integer> {

    Page<Task> findByGroup(Group group, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.group = :group AND LOWER(t.title) LIKE LOWER(CONCAT('%', :filter, '%'))")
    Page<Task> findByGroupAndTitleContainingIgnoreCase(Group group, @Param("filter") String filter, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.group = :group AND LOWER(t.status) LIKE LOWER(CONCAT('%', :filter, '%'))")
    Page<Task> findByGroupAndStatusContainingIgnoreCase(Group group, @Param("filter") String filter, Pageable pageable);

    @Query("SELECT t FROM Task t WHERE t.group = :group AND t.deadLine BETWEEN :startDate AND :endDate")
    Page<Task> findByGroupAndDeadlineBetween(Group group, @Param("startDate") Instant startDate,
                                             @Param("endDate") Instant endDate, Pageable pageable);
}