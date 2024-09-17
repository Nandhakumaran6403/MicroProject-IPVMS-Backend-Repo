package com.visitor.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "Employee")
public class Employee {

	@Id
	@Column(name = "employeeId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int employeeId;

	@ManyToOne
	@JoinColumn(name = "companyId", nullable = false)
	private Company company;

	@Column(name = "employeeName")
	private String employeeName;

	@Column(name = "Position")
	private String position;

	@Column(name = "DOB")
	private LocalDate dob;

	@Column(name = "employeeEmail")
	private String employeeEmail;

	@Column(name = "employeePhone")
	private String employeePhone;

	@Column(name = "employeeAddress")
	private String employeeAddress;

	@Column(name = "employeeCity")
	private String employeeCity;

	@Column(name = "employeeState")
	private String employeeState;

	@Column(name = "employeePassword")
	private String employeePassword;

	@Column(name = "LastLoginDate")
	@Temporal(TemporalType.DATE)
	private Date lastLoginDate;

	@Column(name = "Availability")
	private String availability;

	@Column(name = "Role")
	private String role;

	@Lob
	@Column(name = "ProfileImageBlob", nullable = true, length = 100000000)
	private byte[] profileImageBlob;

	public Employee() {
		super();
	}

	public Employee(int employeeId, Company company, String employeeName, String position, LocalDate dob,
			String employeeEmail, String employeePhone, String employeeAddress, String employeeCity,
			String employeeState, String employeePassword, Date lastLoginDate, String availability, String role,
			byte[] profileImageBlob) {
		super();
		this.employeeId = employeeId;
		this.company = company;
		this.employeeName = employeeName;
		this.position = position;
		this.dob = dob;
		this.employeeEmail = employeeEmail;
		this.employeePhone = employeePhone;
		this.employeeAddress = employeeAddress;
		this.employeeCity = employeeCity;
		this.employeeState = employeeState;
		this.employeePassword = employeePassword;
		this.lastLoginDate = lastLoginDate;
		this.availability = availability;
		this.role = role;
		this.profileImageBlob = profileImageBlob;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public String getEmployeePhone() {
		return employeePhone;
	}

	public void setEmployeePhone(String employeePhone) {
		this.employeePhone = employeePhone;
	}

	public String getEmployeeAddress() {
		return employeeAddress;
	}

	public void setEmployeeAddress(String employeeAddress) {
		this.employeeAddress = employeeAddress;
	}

	public String getEmployeeCity() {
		return employeeCity;
	}

	public void setEmployeeCity(String employeeCity) {
		this.employeeCity = employeeCity;
	}

	public String getEmployeeState() {
		return employeeState;
	}

	public void setEmployeeState(String employeeState) {
		this.employeeState = employeeState;
	}

	public String getEmployeePassword() {
		return employeePassword;
	}

	public void setEmployeePassword(String employeePassword) {
		this.employeePassword = employeePassword;
	}

	public Date getLastLoginDate() {
		return lastLoginDate;
	}

	public void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public byte[] getProfileImageBlob() {
		return profileImageBlob;
	}

	public void setProfileImageBlob(byte[] profileImageBlob) {
		this.profileImageBlob = profileImageBlob;
	}

}
