package ru.salad.taskmanager.taskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.salad.taskmanager.taskmanager.entity.Company;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {
}