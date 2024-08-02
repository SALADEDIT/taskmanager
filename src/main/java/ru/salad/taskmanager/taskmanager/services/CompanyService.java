package ru.salad.taskmanager.taskmanager.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.salad.taskmanager.taskmanager.models.Company;
import ru.salad.taskmanager.taskmanager.repositories.CompanyRepository;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    public Company createGroup(Company company) {
        return null;
        //return groupRepository.findById(group);
    }
}
