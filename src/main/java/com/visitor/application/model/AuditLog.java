package com.visitor.application.model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class AuditLog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int logId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private AdministrativeUser user;

	private String action;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	private String details;

	public AuditLog() {
		super();
	}

	public AuditLog(int logId, AdministrativeUser user, String action, Date date, String details) {
		super();
		this.logId = logId;
		this.user = user;
		this.action = action;
		this.date = date;
		this.details = details;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public AdministrativeUser getUser() {
		return user;
	}

	public void setUser(AdministrativeUser user) {
		this.user = user;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

}
