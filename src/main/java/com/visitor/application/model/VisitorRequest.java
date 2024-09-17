package com.visitor.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;

@Entity
@Table(name = "VisitorRequest")
public class VisitorRequest {

	@Id
	@Column(name = "requestId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int requestId;

	@Column(name = "visitorName")
	private String visitorName;

	@Column(name = "visitorEmail")
	private String visitorEmail;

	@Column(name = "visitorPhone")
	private String visitorPhone;

	@Lob
	@Column(name = "visitorImageBlob", nullable = true, length = 100000000)
	private byte[] visitorImageBlob;

	@ManyToOne
	@JoinColumn(name = "employeeId", nullable = false)
	private Employee employee;

	@Column(name = "status")
	private String status;

	@Column(name = "visitorRequestDateTime")
	private Timestamp visitorRequestDateTime;

	@Column(name = "Reason")
	private String reason;

	public VisitorRequest() {
		super();
	}

	public VisitorRequest(int requestId, String visitorName, String visitorEmail, String visitorPhone,
			byte[] visitorImageBlob, Employee employee, String status, Timestamp visitorRequestDateTime,
			String reason) {
		super();
		this.requestId = requestId;
		this.visitorName = visitorName;
		this.visitorEmail = visitorEmail;
		this.visitorPhone = visitorPhone;
		this.visitorImageBlob = visitorImageBlob;
		this.employee = employee;
		this.status = status;
		this.visitorRequestDateTime = visitorRequestDateTime;
		this.reason = reason;
	}

	public int getRequestId() {
		return requestId;
	}

	public void setRequestId(int requestId) {
		this.requestId = requestId;
	}

	public String getVisitorName() {
		return visitorName;
	}

	public void setVisitorName(String visitorName) {
		this.visitorName = visitorName;
	}

	public String getVisitorEmail() {
		return visitorEmail;
	}

	public void setVisitorEmail(String visitorEmail) {
		this.visitorEmail = visitorEmail;
	}

	public String getVisitorPhone() {
		return visitorPhone;
	}

	public void setVisitorPhone(String visitorPhone) {
		this.visitorPhone = visitorPhone;
	}

	public byte[] getVisitorImageBlob() {
		return visitorImageBlob;
	}

	public void setVisitorImageBlob(byte[] visitorImageBlob) {
		this.visitorImageBlob = visitorImageBlob;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Timestamp getVisitorRequestDateTime() {
		return visitorRequestDateTime;
	}

	public void setVisitorRequestDateTime(Timestamp visitorRequestDateTime) {
		this.visitorRequestDateTime = visitorRequestDateTime;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
