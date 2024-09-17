package com.visitor.application.repositoryimpl;

import com.visitor.application.model.Company;
import com.visitor.application.repository.CompanyRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class CompanyRepositoryImpl implements CompanyRepository {

	@Autowired
	private EntityManager entityManager;

	@Override
	public Company save(Company company) {
		entityManager.persist(company);
		return company;
	}

	@Override
	public Company findById(int companyId) {
		return entityManager.find(Company.class, companyId);
	}

	@Override
	public Company update(Company company) {
		if (company.getCompanyId() > 0) {
			entityManager.merge(company);
		} else {
			throw new IllegalArgumentException("Company ID must be greater than 0 for update.");
		}
		return company;
	}

	@Override
	public void deleteById(int companyId) {
		Company company = entityManager.find(Company.class, companyId);
		if (company != null) {
			entityManager.remove(company);
		}
	}

	@Override
	public List<Company> findAll() {
		return entityManager.createQuery("SELECT c FROM Company c", Company.class).getResultList();
	}
}
