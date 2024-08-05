package ru.salad.taskmanager.taskmanager.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.salad.taskmanager.taskmanager.entity.Company;
import ru.salad.taskmanager.taskmanager.services.CompanyService;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/company", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class CompanyController {


    private final CompanyService companyService;

    @GetMapping("/getCompanyById")
    public Optional<Company> getCompanyById(Integer id) {
        return companyService.getCompanyById(id);
    }

    @PostMapping("/createCompany")
    public ResponseEntity<Company> createCompany(Company company) {
        Company createdCompany = companyService.createCompany(company);
        return new ResponseEntity<>(createdCompany, HttpStatus.CREATED);
    }

    @PostMapping("/updateCompany")
    public Company updateCompanyName(Integer id, @RequestParam String name) {
        return companyService.updateCompanyName(id, name);
    }

    @DeleteMapping("/deleteCompany")
    public void deleteCompany(Integer id) {
        companyService.deleteCompany(id);
    }
}
