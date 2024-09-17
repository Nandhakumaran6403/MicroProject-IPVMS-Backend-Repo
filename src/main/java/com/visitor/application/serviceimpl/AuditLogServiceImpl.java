package com.visitor.application.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.visitor.application.model.AuditLog;
import com.visitor.application.repository.AuditLogRepository;
import com.visitor.application.service.AuditLogService;

@Service
public class AuditLogServiceImpl implements AuditLogService {

	@Autowired
	private AuditLogRepository auditLogRepository;

	@Override
	public AuditLog saveAuditLog(AuditLog auditLog) {
		return auditLogRepository.save(auditLog);
	}

	@Override
	public AuditLog updateAuditLog(int id, AuditLog auditLog) {
		if (auditLogRepository.findById(id) != null) {
			auditLog.setLogId(id);
			return auditLogRepository.update(auditLog);
		}
		return null;
	}

	@Override
	public void deleteAuditLog(int logId) {
		if (auditLogRepository.findById(logId) != null) {
			auditLogRepository.delete(logId);
		} else {
			throw new IllegalArgumentException("AuditLog with ID " + logId + " does not exist.");
		}
	}

	@Override
	public AuditLog getAuditLogById(int logId) {
		return auditLogRepository.findById(logId);
	}

	@Override
	public List<AuditLog> getAllAuditLogs() {
		return auditLogRepository.findAll();
	}
}
