package com.visitor.application.controller;

import com.visitor.application.model.Appointment;
import com.visitor.application.service.AppointmentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/appointments")
public class AppointmentController {

	private final AppointmentService appointmentService;

	public AppointmentController(AppointmentService appointmentService) {
		this.appointmentService = appointmentService;
	}

	@PostMapping
	public Appointment createAppointment(@RequestBody Appointment appointment) {
		return appointmentService.save(appointment);
	}

	@GetMapping("/{id}")
	public Appointment getAppointment(@PathVariable("id") int id) {
		return appointmentService.findById(id);
	}

	@PutMapping("/{id}")
	public Appointment updateAppointment(@RequestBody Appointment appointment) {
		return appointmentService.update(appointment);
	}

	@DeleteMapping("/{id}")
	public void deleteAppointment(@PathVariable("id") int id) {
		appointmentService.delete(id);
	}

	@GetMapping
	public List<Appointment> getAllAppointments() {
		return appointmentService.findAll();
	}
}
