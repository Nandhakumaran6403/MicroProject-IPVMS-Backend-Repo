package com.visitor.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.visitor.application.model.AuditLog;
import com.visitor.application.service.AuditLogService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/audit-logs")
public class AuditLogController {

	@Autowired
	private AuditLogService auditLogService;

	@PostMapping
	public AuditLog createAuditLog(@RequestBody AuditLog auditLog) {
		return auditLogService.saveAuditLog(auditLog);
	}

	@PutMapping("/{id}")
	public AuditLog updateAuditLog(@PathVariable("id") int id, @RequestBody AuditLog auditLog) {
		if (auditLog.getLogId() != id) {
			throw new IllegalArgumentException("AuditLog ID mismatch");
		}
		return auditLogService.updateAuditLog(id, auditLog);
	}

	@DeleteMapping("/{id}")
	public void deleteAuditLog(@PathVariable("id") int id) {
		auditLogService.deleteAuditLog(id);
	}

	@GetMapping("/{id}")
	public AuditLog getAuditLogById(@PathVariable("id") int id) {
		return auditLogService.getAuditLogById(id);
	}

	@GetMapping("/all")
	public List<AuditLog> getAllAuditLogs() {
		return auditLogService.getAllAuditLogs();
	}

}
