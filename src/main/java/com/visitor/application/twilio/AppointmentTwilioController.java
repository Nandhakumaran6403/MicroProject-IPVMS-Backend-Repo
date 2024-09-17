package com.visitor.application.twilio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.visitor.application.model.Employee;
import com.visitor.application.service.EmployeeService;

import org.springframework.http.ResponseEntity;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/appointmenttwilio")
public class AppointmentTwilioController {

	@Autowired
	private SmsService smsService;

	@Autowired
	private EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<String> createAppointmentTwilio(@RequestBody AppointmentDTO appointmentDTO) {

		Employee employee = employeeService.getEmployeeById(appointmentDTO.getEmployee().getEmployeeId());
		String employeeName = employee.getEmployeeName();
		smsService.sendSms(appointmentDTO.getVisitorPhone(), appointmentDTO.getVisitorName(),
				appointmentDTO.getAppointmentDateTime(), employeeName);

		return ResponseEntity.ok("Appointment created and SMS sent");
	}
}
