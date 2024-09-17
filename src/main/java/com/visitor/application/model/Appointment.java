package com.visitor.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import java.time.LocalDateTime;

@Entity
@Table(name = "Appointment")
public class Appointment {

	@Id
	@Column(name = "appointmentId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int appointmentId;

	@Column(name = "appointmentDateTime")
	private LocalDateTime appointmentDateTime;

	@Column(name = "VisitorName")
	private String visitorName;

	@Column(name = "VisitorEmail")
	private String visitorEmail;

	@Column(name = "VisitorPhone")
	private String visitorPhone;

	@Column(name = "Reason")
	private String reason;

	@ManyToOne
	@JoinColumn(name = "employeeId", nullable = false)
	private Employee employee;

	public Appointment() {
		super();
	}

	public Appointment(int appointmentId, LocalDateTime appointmentDateTime, String visitorName, String visitorEmail,
			String visitorPhone, String reason, Employee employee) {
		super();
		this.appointmentId = appointmentId;
		this.appointmentDateTime = appointmentDateTime;
		this.visitorName = visitorName;
		this.visitorEmail = visitorEmail;
		this.visitorPhone = visitorPhone;
		this.reason = reason;
		this.employee = employee;
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
		this.appointmentId = appointmentId;
	}

	public LocalDateTime getAppointmentDateTime() {
		return appointmentDateTime;
	}

	public void setAppointmentDateTime(LocalDateTime appointmentDateTime) {
		this.appointmentDateTime = appointmentDateTime;
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

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
