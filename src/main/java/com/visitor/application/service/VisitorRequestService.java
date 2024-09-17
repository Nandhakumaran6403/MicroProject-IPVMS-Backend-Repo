package com.visitor.application.service;

import java.util.List;

import com.visitor.application.model.VisitorRequest;

public interface VisitorRequestService {

	VisitorRequest saveVisitorRequest(VisitorRequest visitorRequest);

	VisitorRequest getVisitorRequestById(int visitorRequestId);

	List<VisitorRequest> getAllVisitorRequests();

	void deleteVisitorRequest(int visitorRequestId);

	VisitorRequest updateVisitorRequest(int id, VisitorRequest visitorRequest);

}
