package com.visitor.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.visitor.application.model.Company;
import com.visitor.application.model.Employee;
import com.visitor.application.service.CompanyService;
import com.visitor.application.service.EmployeeService;

import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private CompanyService companyService;

	@PostMapping
	public String addEmployee(@RequestParam("companyId") int companyId,
			@RequestParam("employeeName") String employeeName, @RequestParam("position") String position,
			@RequestParam("dob") LocalDate dob, @RequestParam("employeeEmail") String employeeEmail,
			@RequestParam("employeePhone") String employeePhone,
			@RequestParam("employeeAddress") String employeeAddress, @RequestParam("employeeCity") String employeeCity,
			@RequestParam("employeeState") String employeeState,
			@RequestParam("employeePassword") String employeePassword,
			@RequestParam("availability") String availability, @RequestParam("role") String role,
			@RequestParam(value = "profileImageBlob", required = false) MultipartFile profileImageBlob) {

		try {
			byte[] imageBytes = null;
			if (profileImageBlob != null && !profileImageBlob.isEmpty()) {
				if (profileImageBlob.getSize() > 10 * 1024 * 1024) {
					return "File size exceeds the maximum limit of 10MB";
				}
				imageBytes = profileImageBlob.getBytes();
			}

			Employee employee = new Employee();
			Company company = companyService.findById(companyId);
			employee.setCompany(company);
			employee.setEmployeeName(employeeName);
			employee.setPosition(position);
			employee.setDob(dob);
			employee.setEmployeeEmail(employeeEmail);
			employee.setEmployeePhone(employeePhone);
			employee.setEmployeeAddress(employeeAddress);
			employee.setEmployeeCity(employeeCity);
			employee.setEmployeeState(employeeState);
			employee.setEmployeePassword(employeePassword);

			employee.setRole(role);
			employee.setLastLoginDate(new Timestamp(System.currentTimeMillis()));
			employee.setProfileImageBlob(imageBytes);

			employeeService.saveEmployee(employee);
			return "AddSuccess";

		} catch (IOException e) {
			return "AddFailure";
		}
	}

	@GetMapping("/all")
	public List<Employee> viewAllEmployees() {
		return employeeService.getAllEmployees();
	}

	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") int id) {
		return employeeService.getEmployeeById(id);
	}

	@DeleteMapping("/{id}")
	public String deleteEmployeeById(@PathVariable("id") int id) {
		try {
			employeeService.deleteEmployee(id);
			return "DeleteSuccess";
		} catch (Exception e) {
			return "DeleteFailure";
		}
	}

	@PutMapping("/{id}")
	public String updateEmployee(@PathVariable int id, @RequestParam("companyId") int companyId,
			@RequestParam("employeeName") String employeeName, @RequestParam("position") String position,
			@RequestParam("dob") LocalDate dob, @RequestParam("employeeEmail") String employeeEmail,
			@RequestParam("employeePhone") String employeePhone,
			@RequestParam("employeeAddress") String employeeAddress, @RequestParam("employeeCity") String employeeCity,
			@RequestParam("employeeState") String employeeState,
			@RequestParam("employeePassword") String employeePassword,
			@RequestParam("availability") String availability, @RequestParam("role") String role,
			@RequestParam(value = "profileImageBlob", required = false) MultipartFile profileImageBlob) {

		String msg;
		try {
			Employee existingEmployee = employeeService.getEmployeeById(id);
			if (existingEmployee == null) {
				return "Employee not found";
			}

			if (profileImageBlob != null && !profileImageBlob.isEmpty()) {
				if (profileImageBlob.getSize() > 10 * 1024 * 1024) {
					return "File size exceeds the maximum limit of 10MB";
				}
				existingEmployee.setProfileImageBlob(profileImageBlob.getBytes()); // Replace the existing image with
																					// the new one
			}
			Company company = companyService.findById(companyId);
			existingEmployee.setCompany(company);
			existingEmployee.setEmployeeName(employeeName);
			existingEmployee.setPosition(position);
			existingEmployee.setDob(dob);
			existingEmployee.setEmployeeEmail(employeeEmail);
			existingEmployee.setEmployeePhone(employeePhone);
			existingEmployee.setEmployeeAddress(employeeAddress);
			existingEmployee.setEmployeeCity(employeeCity);
			existingEmployee.setEmployeeState(employeeState);
			existingEmployee.setEmployeePassword(employeePassword);
			existingEmployee.setRole(role);
			existingEmployee.setAvailability(availability);

			employeeService.updateEmployee(id, existingEmployee);

			msg = "UpdateSuccess";
		} catch (IOException e) {
			msg = "UpdateFailure";
		}
		return msg;
	}

	@PatchMapping("/lastlogin/{id}")
	public Employee updateLastLoginDate(@PathVariable("id") int id) {
		Employee employee = employeeService.getEmployeeById(id);
		if (employee != null) {
			employee.setLastLoginDate(new Date());
			return employeeService.updateEmployee(id, employee);
		}
		return null;
	}
}
