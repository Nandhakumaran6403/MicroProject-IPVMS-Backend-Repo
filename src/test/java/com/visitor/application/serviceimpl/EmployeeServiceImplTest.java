package com.visitor.application.serviceimpl;

import com.visitor.application.model.Employee;
import com.visitor.application.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EmployeeServiceImplTest {

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Mock
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveEmployee_shouldReturnSavedEmployee() {
        Employee employee = new Employee(1, null, "Nandha", "Developer", LocalDate.of(1990, 5, 15), "nandha@example.com", "123-456-7890", "123 Main St", "City", "State", "password123", new Date(), "Available", "User", new byte[0]);
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee savedEmployee = employeeService.saveEmployee(employee);

        assertNotNull(savedEmployee);
        assertEquals("Nandha", savedEmployee.getEmployeeName());
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void updateEmployee_shouldUpdateEmployee() {
        Employee existingEmployee = new Employee(1, null, "Nandha", "Developer", LocalDate.of(1990, 5, 15), "nandha@example.com", "123-456-7890", "123 Main St", "City", "State", "password123", new Date(), "Available", "User", new byte[0]);
        Employee updatedEmployee = new Employee(1, null, "Suriya", "Senior Developer", LocalDate.of(1990, 5, 15), "suriya@example.com", "123-456-7890", "123 Main St", "City", "State", "password123", new Date(), "Available", "User", new byte[0]);

        when(employeeRepository.findById(1)).thenReturn(existingEmployee);
        when(employeeRepository.update(updatedEmployee)).thenReturn(updatedEmployee);

        Employee result = employeeService.updateEmployee(1, updatedEmployee);

        assertNotNull(result);
        assertEquals("Senior Developer", result.getPosition());
        verify(employeeRepository, times(1)).update(updatedEmployee);
    }

    @Test
    void updateEmployee_shouldReturnNullIfNotFound() {
        Employee employee = new Employee(1, null, "Nandha", "Developer", LocalDate.of(1990, 5, 15), "nandha@example.com", "123-456-7890", "123 Main St", "City", "State", "password123", new Date(), "Available", "User", new byte[0]);

        when(employeeRepository.findById(1)).thenReturn(null);

        Employee result = employeeService.updateEmployee(1, employee);

        assertNull(result);
        verify(employeeRepository, never()).update(employee);
    }

    @Test
    void deleteEmployee_shouldCallDeleteById() {
        doNothing().when(employeeRepository).deleteById(1);
        when(employeeRepository.findById(1)).thenReturn(new Employee(1, null, "Nandha", "Developer", LocalDate.of(1990, 5, 15), "nandha@example.com", "123-456-7890", "123 Main St", "City", "State", "password123", new Date(), "Available", "User", new byte[0]));

        employeeService.deleteEmployee(1);

        verify(employeeRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteEmployee_shouldThrowExceptionIfNotFound() {
        when(employeeRepository.findById(1)).thenReturn(null);

        IllegalArgumentException thrownException = assertThrows(IllegalArgumentException.class, () -> {
            employeeService.deleteEmployee(1);
        });

        assertEquals("Employee with ID 1 does not exist.", thrownException.getMessage());
    }

    @Test
    void getEmployeeById_shouldReturnEmployee() {
        Employee employee = new Employee(1, null, "Nandha", "Developer", LocalDate.of(1990, 5, 15), "nandha@example.com", "123-456-7890", "123 Main St", "City", "State", "password123", new Date(), "Available", "User", new byte[0]);
        when(employeeRepository.findById(1)).thenReturn(employee);

        Employee result = employeeService.getEmployeeById(1);

        assertNotNull(result);
        assertEquals("Nandha", result.getEmployeeName());
    }

    @Test
    void getAllEmployees_shouldReturnListOfEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1, null, "Nandha", "Developer", LocalDate.of(1990, 5, 15), "nandha@example.com", "123-456-7890", "123 Main St", "City", "State", "password123", new Date(), "Available", "User", new byte[0]));
        employees.add(new Employee(2, null, "Suriya", "Manager", LocalDate.of(1985, 10, 25), "suriya@example.com", "987-654-3210", "456 Elm St", "Town", "Region", "password456", new Date(), "Unavailable", "Admin", new byte[0]));

        when(employeeRepository.findAll()).thenReturn(employees);

        List<Employee> result = employeeService.getAllEmployees();

        assertNotNull(result);
        assertEquals(2, result.size());
    }
}
