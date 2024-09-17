package com.visitor.application.repositoryimpl;

import com.visitor.application.model.Appointment;
import com.visitor.application.repository.AppointmentRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class AppointmentRepositoryImpl implements AppointmentRepository {

	private final EntityManager entityManager;

	public AppointmentRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public Appointment save(Appointment appointment) {
		entityManager.persist(appointment);
		return appointment;
	}

	@Override
	public Appointment findById(int id) {
		return entityManager.find(Appointment.class, id);
	}

	@Override
	public Appointment update(Appointment appointment) {
		return entityManager.merge(appointment);
	}

	@Override
	public void delete(int id) {
		Appointment appointment = entityManager.find(Appointment.class, id);
		if (appointment != null) {
			entityManager.remove(appointment);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Appointment> findAll() {
		Query query = entityManager.createQuery("SELECT a FROM Appointment a");
		return query.getResultList();
	}
}
