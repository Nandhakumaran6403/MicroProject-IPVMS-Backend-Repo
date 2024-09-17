package com.visitor.application.serviceimpl;

import com.visitor.application.model.Visit;
import com.visitor.application.repository.VisitRepository;
import com.visitor.application.service.VisitService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class VisitServiceImpl implements VisitService {

	private final VisitRepository visitRepository;

	public VisitServiceImpl(VisitRepository visitRepository) {
		this.visitRepository = visitRepository;
	}

	@Override
	public Visit saveVisit(Visit visit) {
		return visitRepository.save(visit);
	}

	@Override
	public Visit getVisitById(int visitId) {
		return visitRepository.findById(visitId);
	}

	@Override
	public void deleteVisitById(int visitId) {
		visitRepository.deleteById(visitId);
	}

	@Override
	public Visit updateVisit(Visit visit) {
		return visitRepository.update(visit);
	}

	@Override
	public List<Visit> getAllVisits() {
		return visitRepository.findAll();
	}
}
