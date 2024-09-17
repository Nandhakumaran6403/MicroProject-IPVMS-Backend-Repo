package com.visitor.application.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.visitor.application.model.Employee;
import com.visitor.application.model.VisitorRequest;
import com.visitor.application.service.EmployeeService;
import com.visitor.application.service.VisitorRequestService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/visitorrequests")
public class VisitorRequestController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private VisitorRequestService visitorRequestService;

	@PostMapping
	public Object addVisitorReqeust(@RequestParam("employeeId") int employeeId,
			@RequestParam("visitorName") String visitorName, @RequestParam("visitorEmail") String visitorEmail,
			@RequestParam("visitorPhone") String visitorPhone, @RequestParam("status") String status,
			@RequestParam("reason") String reason,
			@RequestParam(value = "visitorImageBlob", required = false) MultipartFile visitorImageBlob) {

		try {
			byte[] imageBytes = null;
			if (visitorImageBlob != null && !visitorImageBlob.isEmpty()) {
				if (visitorImageBlob.getSize() > 10 * 1024 * 1024) {
					return null;
				}
				imageBytes = visitorImageBlob.getBytes();
			}

			VisitorRequest visitorRequest = new VisitorRequest();
			Employee employee = employeeService.getEmployeeById(employeeId);
			visitorRequest.setEmployee(employee);
			visitorRequest.setVisitorRequestDateTime(new Timestamp(System.currentTimeMillis()));
			visitorRequest.setReason(reason);
			visitorRequest.setStatus(status);
			visitorRequest.setVisitorEmail(visitorEmail);
			visitorRequest.setVisitorName(visitorName);
			visitorRequest.setVisitorPhone(visitorPhone);
			visitorRequest.setVisitorImageBlob(imageBytes);

			return visitorRequestService.saveVisitorRequest(visitorRequest);

		} catch (IOException e) {
		}
		return null;
	}

	@GetMapping("/all")
	public List<VisitorRequest> viewVisitorRequests() {
		return visitorRequestService.getAllVisitorRequests();
	}

	@GetMapping("/{id}")
	public VisitorRequest getVisitorRequestById(@PathVariable("id") int id) {
		return visitorRequestService.getVisitorRequestById(id);
	}

	@DeleteMapping("/{id}")
	public String deleteVisitorRequestById(@PathVariable("id") int id) {
		try {
			visitorRequestService.deleteVisitorRequest(id);
			return "DeleteSuccess";
		} catch (Exception e) {
			return "DeleteFailure";
		}
	}

	@PatchMapping("/statusupdate/{id}")
	public VisitorRequest updateLastLoginDate(@PathVariable("id") int id, @RequestParam("status") String status) {
		VisitorRequest visitorRequest = visitorRequestService.getVisitorRequestById(id);
		if (visitorRequest != null) {
			visitorRequest.setStatus(status);
			return visitorRequestService.updateVisitorRequest(id, visitorRequest);
		}
		return null;
	}
}
