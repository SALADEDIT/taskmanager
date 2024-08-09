package ru.salad.taskmanager.taskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.salad.taskmanager.taskmanager.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

}