package com.visitor.application.service;

import com.visitor.application.model.Company;

import java.util.List;

public interface CompanyService {
	Company save(Company company);

	Company findById(int companyId);

	Company update(Company company);

	void deleteById(int companyId);

	List<Company> findAll();
}
