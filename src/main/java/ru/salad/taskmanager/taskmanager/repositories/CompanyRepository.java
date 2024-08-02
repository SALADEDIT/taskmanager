package ru.salad.taskmanager.taskmanager.repositories;

import org.springframework.data.repository.Repository;
import ru.salad.taskmanager.taskmanager.models.Company;

import java.util.Optional;

public interface CompanyRepository extends Repository<Company, Integer> {
    Optional<Company> findById(Integer id);

}