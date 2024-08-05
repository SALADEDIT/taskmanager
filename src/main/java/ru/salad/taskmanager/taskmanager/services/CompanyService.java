package ru.salad.taskmanager.taskmanager.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.salad.taskmanager.taskmanager.entity.Company;
import ru.salad.taskmanager.taskmanager.repositories.CompanyRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRepository companyRepository;


    public Optional<Company> getCompanyById(Integer id) {
        return Optional.ofNullable(companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Компания с ID" + id + " не найдена")));
    }

    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    public Company updateCompanyName(Integer companyId, String name) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Компания не найдена"));
        company.setName(name);
        return companyRepository.save(company);
    }

    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }
}
