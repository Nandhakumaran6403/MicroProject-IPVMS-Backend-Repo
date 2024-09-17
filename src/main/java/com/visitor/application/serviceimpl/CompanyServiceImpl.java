package com.visitor.application.serviceimpl;

import com.visitor.application.model.Company;
import com.visitor.application.repository.CompanyRepository;
import com.visitor.application.service.CompanyService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {

	private final CompanyRepository companyRepository;

	public CompanyServiceImpl(CompanyRepository companyRepository) {
		this.companyRepository = companyRepository;
	}

	@Override
	public Company save(Company company) {
		return companyRepository.save(company);
	}

	@Override
	public Company findById(int companyId) {
		return companyRepository.findById(companyId);
	}

	@Override
	public Company update(Company company) {
		if (company != null && company.getCompanyId() > 0) {
			companyRepository.update(company);
		} else {
			throw new IllegalArgumentException("Invalid company data for update.");
		}
		return company;
	}

	@Override
	public void deleteById(int companyId) {
		Company company = companyRepository.findById(companyId);
		if (company != null) {
			companyRepository.deleteById(companyId);
		} else {
			throw new IllegalArgumentException("Company not found for deletion.");
		}
	}

	@Override
	public List<Company> findAll() {
		return companyRepository.findAll();
	}
}
