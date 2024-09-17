package com.visitor.application.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

import java.sql.Timestamp;

@Entity
@Table(name = "Visit")
public class Visit {

	@Id
	@Column(name = "visitId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int visitId;

	@ManyToOne
	@JoinColumn(name = "requestId", nullable = false)
	private VisitorRequest visitorRequest;

	@Column(name = "checkInDateTime", nullable = true)
	private Timestamp checkInDateTime;

	@Column(name = "checkOutDateTime", nullable = true)
	private Timestamp checkOutDateTime;

	@Column(name = "fromDateTime")
	private Timestamp fromDateTime;

	@Column(name = "toDateTime")
	private Timestamp toDateTime;

	@Column(name = "checkIn", nullable = true)
	private String checkIn;

	@Column(name = "checkOut", nullable = true)
	private String checkOut;

	@Column(name = "visitingStatus", nullable = true)
	private String visitingStatus;

	@Column(name = "type")
	private String type;

	@Column(name = "blockStatus", nullable = true)
	private String blockStatus;

	@Column(name = "totalVisitingDuration", nullable = true)
	private long totalVisitingDuration;

	public Visit() {
		super();
	}

	public Visit(int visitId, VisitorRequest visitorRequest, Timestamp checkInDateTime, Timestamp checkOutDateTime,
			Timestamp fromDateTime, Timestamp toDateTime, String checkIn, String checkOut, String visitingStatus,
			String type, String blockStatus, long totalVisitingDuration) {
		super();
		this.visitId = visitId;
		this.visitorRequest = visitorRequest;
		this.checkInDateTime = checkInDateTime;
		this.checkOutDateTime = checkOutDateTime;
		this.fromDateTime = fromDateTime;
		this.toDateTime = toDateTime;
		this.checkIn = checkIn;
		this.checkOut = checkOut;
		this.visitingStatus = visitingStatus;
		this.type = type;
		this.blockStatus = blockStatus;
		this.totalVisitingDuration = totalVisitingDuration;
	}

	public int getVisitId() {
		return visitId;
	}

	public void setVisitId(int visitId) {
		this.visitId = visitId;
	}

	public VisitorRequest getVisitorRequest() {
		return visitorRequest;
	}

	public void setVisitorRequest(VisitorRequest visitorRequest) {
		this.visitorRequest = visitorRequest;
	}

	public Timestamp getCheckInDateTime() {
		return checkInDateTime;
	}

	public void setCheckInDateTime(Timestamp checkInDateTime) {
		this.checkInDateTime = checkInDateTime;
	}

	public Timestamp getCheckOutDateTime() {
		return checkOutDateTime;
	}

	public void setCheckOutDateTime(Timestamp checkOutDateTime) {
		this.checkOutDateTime = checkOutDateTime;
	}

	public Timestamp getFromDateTime() {
		return fromDateTime;
	}

	public void setFromDateTime(Timestamp fromDateTime) {
		this.fromDateTime = fromDateTime;
	}

	public Timestamp getToDateTime() {
		return toDateTime;
	}

	public void setToDateTime(Timestamp toDateTime) {
		this.toDateTime = toDateTime;
	}

	public String getCheckIn() {
		return checkIn;
	}

	public void setCheckIn(String checkIn) {
		this.checkIn = checkIn;
	}

	public String getCheckOut() {
		return checkOut;
	}

	public void setCheckOut(String checkOut) {
		this.checkOut = checkOut;
	}

	public String getVisitingStatus() {
		return visitingStatus;
	}

	public void setVisitingStatus(String visitingStatus) {
		this.visitingStatus = visitingStatus;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBlockStatus() {
		return blockStatus;
	}

	public void setBlockStatus(String blockStatus) {
		this.blockStatus = blockStatus;
	}

	public long getTotalVisitingDuration() {
		return totalVisitingDuration;
	}

	public void setTotalVisitingDuration(long totalVisitingDuration) {
		this.totalVisitingDuration = totalVisitingDuration;
	}

	@Override
	public String toString() { 
		return "\r\n\r\nVisitId: " + visitId + "\r\n\r\n" + "Visitor Name: " + visitorRequest.getVisitorName()+ "\r\n\r\n" + "Visitor Email: " + visitorRequest.getVisitorEmail() 
		+"\r\n\r\n" + "Visitor Phone: "+ visitorRequest.getVisitorPhone()+ "\r\n\r\n"+ "Reason For Visit: "+visitorRequest.getReason()+"\r\n\r\n" + "Employee Name: "+ visitorRequest.getEmployee().getEmployeeName()+ "\r\n\r\n"  + "CheckInDateTime:"
		 	+ checkInDateTime +"\r\n\r\n" +  "CheckOutDateTime:" + checkOutDateTime + "\r\n\r\n"+  "FromDateTime:" + fromDateTime
			+ "\r\n\r\n"+ "ToDateTime: " + toDateTime+"\r\n\r\n" + "CheckIn: " + checkIn+"\r\n\r\n" + "CheckOut: " + checkOut+ "\r\n\r\n" + "Visiting Status: "
			+ visitingStatus+"\r\n\r\n" + "Type:" + type+"\r\n\r\n" + "Block Status: " + blockStatus+"\r\n\r\n" + "TotalVisitingDuration: "
				+ totalVisitingDuration + "\r\n\r\n\r\n\r\n" ;
	}
	
	

}
