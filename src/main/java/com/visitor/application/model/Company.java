package com.visitor.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDate;

import jakarta.persistence.Column;

@Entity
@Table(name = "Company")
public class Company {

	@Id
	@Column(name = "companyId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int companyId;

	@Column(name = "companyName")
	private String companyName;

	@Column(name = "companyFloorNo")
	private Integer companyFloorNo;

	@Column(name = "companyFounder")
	private String companyFounder;

	@Column(name = "companyStartDate")
	private LocalDate companyStartDate;

	public Company() {
		super();
	}

	public Company(int companyId, String companyName, Integer companyFloorNo, String companyFounder,
			LocalDate companyStartDate) {
		super();
		this.companyId = companyId;
		this.companyName = companyName;
		this.companyFloorNo = companyFloorNo;
		this.companyFounder = companyFounder;
		this.companyStartDate = companyStartDate;
	}

	public int getCompanyId() {
		return companyId;
	}

	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getCompanyFloorNo() {
		return companyFloorNo;
	}

	public void setCompanyFloorNo(Integer companyFloorNo) {
		this.companyFloorNo = companyFloorNo;
	}

	public String getCompanyFounder() {
		return companyFounder;
	}

	public void setCompanyFounder(String companyFounder) {
		this.companyFounder = companyFounder;
	}

	public LocalDate getCompanyStartDate() {
		return companyStartDate;
	}

	public void setCompanyStartDate(LocalDate companyStartDate) {
		this.companyStartDate = companyStartDate;
	}

}
