package service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import ru.salad.taskmanager.taskmanager.entity.Company;
import ru.salad.taskmanager.taskmanager.entity.Status;
import ru.salad.taskmanager.taskmanager.entity.Task;
import ru.salad.taskmanager.taskmanager.repositories.CompanyRepository;
import ru.salad.taskmanager.taskmanager.repositories.TaskRepository;
import ru.salad.taskmanager.taskmanager.services.CompanyService;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest()
@ContextConfiguration(classes = CompanyService.class)
public class CompanyServiceTest {
    @MockBean
    private TaskRepository taskRepository;
    @MockBean
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyService companyService;
    private static final Integer COMPANY_ID = 1;
    private static final String COMPANY_NAME = "Test Company";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCompanyById() {
        Optional<Company> toBeReturned = Optional.of(new Company(COMPANY_ID, COMPANY_NAME));

        doReturn(toBeReturned).when(companyRepository).findById(COMPANY_ID);

        Optional<Company> companyById = companyService.getCompanyById(COMPANY_ID);

        assertThat(companyById).isPresent();
        assertThat(companyById.get()).isEqualTo(toBeReturned.get());
    }

    @Test
    void testCreateCompany() {
        Company company = new Company(COMPANY_ID, COMPANY_NAME);
        when(companyRepository.save(company)).thenReturn(company);

        Company createdCompany = companyService.createCompany(company);

        assertThat(createdCompany).isNotNull();
        assertThat(createdCompany.getName()).isEqualTo(COMPANY_NAME);
        verify(companyRepository, times(1)).save(company);
    }

    @Test
    void testUpdateCompanyName() {
        Company company = new Company(COMPANY_ID, COMPANY_NAME);
        String newName = "New name";
        when(companyRepository.findById(COMPANY_ID)).thenReturn(Optional.of(company));
        when(companyRepository.save(any(Company.class))).thenReturn(company);

        Company updatedCompany = companyService.updateCompanyName(COMPANY_ID, newName);

        assertNotNull(updatedCompany);
        assertEquals(newName, updatedCompany.getName());
        verify(companyRepository).findById(COMPANY_ID);
        verify(companyRepository).save(company);
    }

    @Test
    void testDeleteCompany() {
        companyService.deleteCompany(COMPANY_ID);
        verify(companyRepository, times(1)).deleteById(COMPANY_ID);
    }

}
