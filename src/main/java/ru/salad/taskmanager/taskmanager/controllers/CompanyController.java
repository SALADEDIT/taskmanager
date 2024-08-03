package ru.salad.taskmanager.taskmanager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.salad.taskmanager.taskmanager.entity.Company;
import ru.salad.taskmanager.taskmanager.services.CompanyService;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/company", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)

public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @GetMapping("/getCompany")
    public Company getById(Integer id) {
        return companyService.getById(id);
    }

}
