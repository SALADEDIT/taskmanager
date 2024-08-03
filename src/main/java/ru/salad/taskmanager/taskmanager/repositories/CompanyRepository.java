package ru.salad.taskmanager.taskmanager.repositories;

import org.springframework.data.repository.Repository;
import ru.salad.taskmanager.taskmanager.entity.Company;


public interface CompanyRepository extends Repository<Company, Integer> {
    Company findById(Integer id);
}