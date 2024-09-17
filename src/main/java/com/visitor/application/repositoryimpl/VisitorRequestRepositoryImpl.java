package com.visitor.application.repositoryimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.visitor.application.model.VisitorRequest;
import com.visitor.application.repository.VisitorRequestRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

@Repository
@Transactional
public class VisitorRequestRepositoryImpl implements VisitorRequestRepository {

	@Autowired
	private EntityManager entityManager;

	public VisitorRequest save(VisitorRequest visitorRequest) {
		entityManager.persist(visitorRequest);
		return visitorRequest;
	}

	@SuppressWarnings("unchecked")
	public List<VisitorRequest> findAll() {
		Query query = entityManager.createQuery("from VisitorRequest");
		return query.getResultList();
	}

	public VisitorRequest findById(int id) {
		return entityManager.find(VisitorRequest.class, id);
	}

	public void deleteById(int id) {
		VisitorRequest visitorRequest = entityManager.find(VisitorRequest.class, id);
		if (visitorRequest != null) {
			entityManager.remove(visitorRequest);
		}
	}

	public VisitorRequest update(VisitorRequest visitorRequest) {
		return entityManager.merge(visitorRequest);
	}
}
