package com.visitor.application.service;

import com.visitor.application.model.Appointment;
import java.util.List;

public interface AppointmentService {

	Appointment save(Appointment appointment);

	Appointment findById(int id);

	Appointment update(Appointment appointment);

	void delete(int id);

	List<Appointment> findAll();
}
