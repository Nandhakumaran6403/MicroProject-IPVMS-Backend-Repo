package com.visitor.application.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visitor.application.model.Employee;
import com.visitor.application.repository.EmployeeRepository;
import com.visitor.application.service.EmployeeService;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee saveEmployee(Employee employee) {
		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(int id, Employee employee) {
		if (employeeRepository.findById(id) != null) {
			employee.setEmployeeId(id);
			return employeeRepository.update(employee);
		}
		return null;
	}

	@Override
	public void deleteEmployee(int employeeId) {
		if (employeeRepository.findById(employeeId) != null) {
			employeeRepository.deleteById(employeeId);
		} else {
			throw new IllegalArgumentException("Employee with ID " + employeeId + " does not exist.");
		}
	}

	@Override
	public Employee getEmployeeById(int employeeId) {
		return employeeRepository.findById(employeeId);
	}

	@Override
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

}
