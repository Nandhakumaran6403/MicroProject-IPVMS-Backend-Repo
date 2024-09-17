package com.visitor.application.repository;

import java.util.List;

import com.visitor.application.model.Employee;

public interface EmployeeRepository {

	Employee save(Employee employee);

	List<Employee> findAll();

	Employee findById(int id);

	void deleteById(int id);

	Employee update(Employee employee);

}
