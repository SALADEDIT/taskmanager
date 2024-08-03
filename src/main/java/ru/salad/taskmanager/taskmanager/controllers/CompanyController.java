package ru.salad.taskmanager.taskmanager.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.salad.taskmanager.taskmanager.entity.Company;
import ru.salad.taskmanager.taskmanager.services.CompanyService;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/company", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)

public class CompanyController {


    private CompanyService companyService;

    @GetMapping("/getCompany")
    public Optional<Company> getById(Integer id) {
        return companyService.getById(id);
    }

    @PostMapping("/createCompany")
    public ResponseEntity<Company> createCompany(Company company) {
        Company createdCompany = companyService.createCompany(company);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }
}
