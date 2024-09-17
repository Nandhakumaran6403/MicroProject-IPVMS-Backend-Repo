package com.visitor.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.visitor.application.model.AdministrativeUser;
import com.visitor.application.model.Employee;
import com.visitor.application.model.Login;
import com.visitor.application.service.AdministrativeUserService;
import com.visitor.application.service.EmployeeService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private AdministrativeUserService administrativeUserService;

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public Object login(@RequestBody Login login) {

		String email = login.getEmail();
		String password = login.getPassword();

		List<AdministrativeUser> admins = administrativeUserService.getAllAdministrativeUsers();
		for (AdministrativeUser admin : admins) {
			if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
				return admin;
			}
		}

		List<Employee> employees = employeeService.getAllEmployees();
		for (Employee employee : employees) {
			if (employee.getEmployeeEmail().equals(email) && employee.getEmployeePassword().equals(password)) {
				return employee;
			}
		}

		return "Login failed: Invalid email or password";
	}
}