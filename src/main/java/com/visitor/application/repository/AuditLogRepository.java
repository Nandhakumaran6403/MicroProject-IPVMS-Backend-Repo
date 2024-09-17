package com.visitor.application.repository;

import java.util.List;

import com.visitor.application.model.AuditLog;

public interface AuditLogRepository {

	public AuditLog findById(int id);

	public List<AuditLog> findAll();

	public AuditLog save(AuditLog auditLog);

	public AuditLog update(AuditLog auditLog);

	public void delete(int id);
}
