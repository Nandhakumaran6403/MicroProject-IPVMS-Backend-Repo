package com.visitor.application.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visitor.application.model.VisitorRequest;
import com.visitor.application.repository.VisitorRequestRepository;
import com.visitor.application.service.VisitorRequestService;

@Service
public class VisitorRequestServiceImpl implements VisitorRequestService {

	@Autowired
	private VisitorRequestRepository visitorRequestRepository;

	@Override
	public VisitorRequest saveVisitorRequest(VisitorRequest visitorRequest) {
		return visitorRequestRepository.save(visitorRequest);
	}

	@Override
	public VisitorRequest updateVisitorRequest(int id, VisitorRequest visitorRequest) {
		if (visitorRequestRepository.findById(id) != null) {
			visitorRequest.setRequestId(id);
			return visitorRequestRepository.update(visitorRequest);
		}
		return null;
	}

	@Override
	public void deleteVisitorRequest(int visitorRequestId) {
		if (visitorRequestRepository.findById(visitorRequestId) != null) {
			visitorRequestRepository.deleteById(visitorRequestId);
		} else {
			throw new IllegalArgumentException("VisitorRequest with ID " + visitorRequestId + " does not exist.");
		}
	}

	@Override
	public VisitorRequest getVisitorRequestById(int visitorRequestId) {
		return visitorRequestRepository.findById(visitorRequestId);
	}

	@Override
	public List<VisitorRequest> getAllVisitorRequests() {
		return visitorRequestRepository.findAll();
	}

}
