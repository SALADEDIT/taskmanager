package ru.salad.taskmanager.taskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.salad.taskmanager.taskmanager.entity.Company;

import java.util.Optional;


public interface CompanyRepository extends JpaRepository<Company, Integer> {
    Optional<Company> findById(Integer id);


}