package com.visitor.application.service;

import java.util.List;

import com.visitor.application.model.Employee;

public interface EmployeeService {

	Employee saveEmployee(Employee employee);

	Employee getEmployeeById(int employeeId);

	List<Employee> getAllEmployees();

	void deleteEmployee(int employeeId);

	Employee updateEmployee(int id, Employee employee);

}
