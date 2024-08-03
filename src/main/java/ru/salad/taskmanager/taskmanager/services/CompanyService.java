package ru.salad.taskmanager.taskmanager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.salad.taskmanager.taskmanager.entity.Company;
import ru.salad.taskmanager.taskmanager.repositories.CompanyRepository;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;

    public Company getById(Integer id) {
        return companyRepository.findById(id);
    }

}
