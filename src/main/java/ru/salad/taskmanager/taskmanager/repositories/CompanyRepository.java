package ru.salad.taskmanager.taskmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.salad.taskmanager.taskmanager.entity.Company;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Optional<Company> findById(Integer id);
}