package com.visitor.application.repository;

import java.util.List;

import com.visitor.application.model.VisitorRequest;

public interface VisitorRequestRepository {

	VisitorRequest save(VisitorRequest visitorRequest);

	List<VisitorRequest> findAll();

	VisitorRequest findById(int id);

	void deleteById(int id);

	VisitorRequest update(VisitorRequest visitorRequest);

}
