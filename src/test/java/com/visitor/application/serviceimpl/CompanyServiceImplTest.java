package com.visitor.application.serviceimpl;

import com.visitor.application.model.Company;
import com.visitor.application.repository.CompanyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CompanyServiceImplTest {

    @InjectMocks
    private CompanyServiceImpl companyService;

    @Mock
    private CompanyRepository companyRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void save_shouldReturnSavedCompany() {
        Company company = new Company(1, "Tech Innovations", 5, "Nandha", LocalDate.of(2000, 1, 1));
        when(companyRepository.save(company)).thenReturn(company);

        Company savedCompany = companyService.save(company);

        assertNotNull(savedCompany);
        assertEquals("Tech Innovations", savedCompany.getCompanyName());
        verify(companyRepository, times(1)).save(company);
    }

    @Test
    void findById_shouldReturnCompany() {
        Company company = new Company(1, "Tech Innovations", 5, "Nandha", LocalDate.of(2000, 1, 1));
        when(companyRepository.findById(1)).thenReturn(company);

        Company foundCompany = companyService.findById(1);

        assertNotNull(foundCompany);
        assertEquals("Tech Innovations", foundCompany.getCompanyName());
    }

    @Test
    void update_shouldUpdateCompany() {
        Company company = new Company(1, "Tech Innovations", 5, "Nandha", LocalDate.of(2000, 1, 1));
        when(companyRepository.findById(1)).thenReturn(company);
        when(companyRepository.update(company)).thenReturn(company);

        Company updatedCompany = companyService.update(company);

        assertNotNull(updatedCompany);
        assertEquals("Tech Innovations", updatedCompany.getCompanyName());
        verify(companyRepository, times(1)).update(company);
    }

    @Test
    void update_shouldThrowExceptionForInvalidCompany() {
        Company company = new Company(0, "Tech Innovations", 5, "Nandha", LocalDate.of(2000, 1, 1));

        IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class, () -> {
            companyService.update(company);
        });

        assertEquals("Invalid company data for update.", thrownException.getMessage());
    }

    @Test
    void deleteById_shouldCallDeleteById() {
        doNothing().when(companyRepository).deleteById(1);
        when(companyRepository.findById(1)).thenReturn(new Company(1, "Tech Innovations", 5, "Nandha", LocalDate.of(2000, 1, 1)));

        companyService.deleteById(1);

        verify(companyRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteById_shouldThrowExceptionIfCompanyNotFound() {
        when(companyRepository.findById(1)).thenReturn(null);

        IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class, () -> {
            companyService.deleteById(1);
        });

        assertEquals("Company not found for deletion.", thrownException.getMessage());
    }

    @Test
    void findAll_shouldReturnListOfCompanies() {
        List<Company> companyList = new ArrayList<>();
        companyList.add(new Company(1, "Tech Innovations", 5, "Nandha", LocalDate.of(2000, 1, 1)));
        companyList.add(new Company(2, "Innovative Solutions", 10, "Suriya", LocalDate.of(2010, 5, 15)));
        when(companyRepository.findAll()).thenReturn(companyList);

        List<Company> allCompanies = companyService.findAll();

        assertNotNull(allCompanies);
        assertEquals(2, allCompanies.size());
    }
}
