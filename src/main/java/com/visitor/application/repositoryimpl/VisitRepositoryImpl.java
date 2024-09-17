package com.visitor.application.repositoryimpl;

import com.visitor.application.model.Visit;
import com.visitor.application.repository.VisitRepository;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Transactional
@Repository
public class VisitRepositoryImpl implements VisitRepository {

	@Autowired
	private EntityManager entityManager;

	@Override
	public Visit save(Visit visit) {
		entityManager.persist(visit);
		return visit;
	}

	@Override
	public Visit findById(int visitId) {
		return entityManager.find(Visit.class, visitId);
	}

	@Override
	public void deleteById(int visitId) {
		Visit visit = entityManager.find(Visit.class, visitId);
		if (visit != null) {
			entityManager.remove(visit);
		}
	}

	@Override
	public Visit update(Visit visit) {
		return entityManager.merge(visit);
	}

	@Override
	public List<Visit> findAll() {
		return entityManager.createQuery("SELECT v FROM Visit v", Visit.class).getResultList();
	}
}
