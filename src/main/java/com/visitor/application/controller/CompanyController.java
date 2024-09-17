package com.visitor.application.controller;

import com.visitor.application.model.Company;
import com.visitor.application.service.CompanyService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/companies")
public class CompanyController {

	private final CompanyService companyService;

	public CompanyController(CompanyService companyService) {
		this.companyService = companyService;
	}

	@PostMapping
	public Company createCompany(@RequestBody Company company) {
		return companyService.save(company);
	}

	@GetMapping("/{id}")
	public Company getCompanyById(@PathVariable("id") int companyId) {
		Company company = companyService.findById(companyId);
		if (company != null) {
			return company;
		}
		return null;
	}

	@PutMapping("/{id}")
	public Company updateCompany(@PathVariable("id") int companyId, @RequestBody Company company) {
		company.setCompanyId(companyId);
		Company existingCompany = companyService.findById(companyId);
		if (existingCompany != null) {
			return companyService.update(company);
		}
		return existingCompany;
	}

	@DeleteMapping("/{id}")
	public String deleteCompany(@PathVariable("id") int companyId) {
		Company existingCompany = companyService.findById(companyId);
		if (existingCompany != null) {
			companyService.deleteById(companyId);
			return "Company deleted successfully";
		}
		return "Company not found for deletion";
	}

	@GetMapping
	public List<Company> getAllCompanies() {
		return companyService.findAll();
	}
}
