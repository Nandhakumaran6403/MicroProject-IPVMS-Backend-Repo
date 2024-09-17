package com.visitor.application.repository;

import com.visitor.application.model.Visit;
import java.util.List;

public interface VisitRepository {
	Visit save(Visit visit);

	Visit findById(int visitId);

	void deleteById(int visitId);

	Visit update(Visit visit);

	List<Visit> findAll();
}
