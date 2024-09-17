package com.visitor.application.service;

import com.visitor.application.model.Visit;
import java.util.List;

public interface VisitService {
	Visit saveVisit(Visit visit);

	Visit getVisitById(int visitId);

	void deleteVisitById(int visitId);

	Visit updateVisit(Visit visit);

	List<Visit> getAllVisits();
}
