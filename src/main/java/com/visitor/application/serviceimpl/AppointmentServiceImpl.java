package com.visitor.application.serviceimpl;

import com.visitor.application.model.Appointment;
import com.visitor.application.repository.AppointmentRepository;
import com.visitor.application.service.AppointmentService;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {

	private AppointmentRepository appointmentRepository;

	public AppointmentServiceImpl(AppointmentRepository appointmentRepository) {
		this.appointmentRepository = appointmentRepository;
	}

	@Override
	public Appointment save(Appointment appointment) {
		return appointmentRepository.save(appointment);
	}

	@Override
	public Appointment findById(int id) {
		return appointmentRepository.findById(id);
	}

	@Override
	public Appointment update(Appointment appointment) {
		return appointmentRepository.update(appointment);
	}

	@Override
	public void delete(int id) {
		appointmentRepository.delete(id);
	}

	@Override
	public List<Appointment> findAll() {
		return appointmentRepository.findAll();
	}
}
