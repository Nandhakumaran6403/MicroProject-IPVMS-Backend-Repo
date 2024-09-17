package com.visitor.application.repositoryimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.visitor.application.model.AdministrativeUser;
import com.visitor.application.repository.AdministrativeUserRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

@Repository
@Transactional
public class AdministrativeUserRepositoryImpl implements AdministrativeUserRepository {

	@Autowired
	private EntityManager entityManager;

	public AdministrativeUser save(AdministrativeUser administrativeUser) {
		entityManager.persist(administrativeUser);
		return administrativeUser;
	}

	@SuppressWarnings("unchecked")
	public List<AdministrativeUser> findAll() {
		Query query = entityManager.createQuery("from AdministrativeUser");
		return query.getResultList();
	}

	public AdministrativeUser findById(int id) {
		return entityManager.find(AdministrativeUser.class, id);
	}

	public void deleteById(int id) {
		AdministrativeUser administrativeUser = entityManager.find(AdministrativeUser.class, id);
		if (administrativeUser != null) {
			entityManager.remove(administrativeUser);
		}
	}

	public AdministrativeUser update(AdministrativeUser administrativeUser) {
		return entityManager.merge(administrativeUser);
	}
}
