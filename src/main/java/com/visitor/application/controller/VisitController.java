package com.visitor.application.controller;

import com.visitor.application.model.Visit;
import com.visitor.application.service.VisitService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/visits")
public class VisitController {

	private final VisitService visitService;

	public VisitController(VisitService visitService) {
		this.visitService = visitService;
	}

	@PostMapping
	public Visit createVisit(@RequestBody Visit visit) {
		return visitService.saveVisit(visit);
	}

	@GetMapping("/{visitId}")
	public Visit getVisitById(@PathVariable int visitId) {
		Visit visit = visitService.getVisitById(visitId);
		if (visit != null) {
			return visit;
		}
		return null;
	}

	@GetMapping
	public List<Visit> getAllVisits() {
		return visitService.getAllVisits();
	}

	@PutMapping("/{visitId}")
	public Visit updateVisit(@PathVariable int visitId, @RequestBody Visit visit) {
		Visit existingVisit = visitService.getVisitById(visitId);
		if (existingVisit != null) {
			visit.setVisitId(visitId);
			return visitService.updateVisit(visit);
		}
		return null;
	}

	@DeleteMapping("/{visitId}")
	public void deleteVisit(@PathVariable int visitId) {
		Visit existingVisit = visitService.getVisitById(visitId);
		if (existingVisit != null) {
			visitService.deleteVisitById(visitId);
		}
	}
}
