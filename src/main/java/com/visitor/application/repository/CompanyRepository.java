package com.visitor.application.repository;

import com.visitor.application.model.Company;

import java.util.List;

public interface CompanyRepository {
	Company save(Company company);

	Company findById(int companyId); // Change to Company

	Company update(Company company);

	void deleteById(int companyId);

	List<Company> findAll();
}
