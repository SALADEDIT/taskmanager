package ru.salad.taskmanager.taskmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.salad.taskmanager.taskmanager.models.Company;
import ru.salad.taskmanager.taskmanager.services.CompanyService;

@RestController
@RequestMapping("/groups")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public Company createGroup(@RequestBody Company company) {
        return companyService.createGroup(company);
    }
}
