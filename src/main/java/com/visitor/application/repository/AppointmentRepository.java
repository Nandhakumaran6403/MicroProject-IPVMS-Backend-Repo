package com.visitor.application.repository;

import com.visitor.application.model.Appointment;
import java.util.List;

public interface AppointmentRepository {

	Appointment save(Appointment appointment);

	Appointment findById(int id);

	Appointment update(Appointment appointment);

	void delete(int id);

	List<Appointment> findAll();
}
