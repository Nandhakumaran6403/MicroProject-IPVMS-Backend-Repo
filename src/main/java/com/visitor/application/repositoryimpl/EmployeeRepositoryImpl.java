package com.visitor.application.repositoryimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.visitor.application.model.Employee;
import com.visitor.application.repository.EmployeeRepository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;

import java.util.List;

@Repository
@Transactional
public class EmployeeRepositoryImpl implements EmployeeRepository {

	@Autowired
	private EntityManager entityManager;

	public Employee save(Employee employee) {
		entityManager.persist(employee);
		return employee;
	}

	@SuppressWarnings("unchecked")
	public List<Employee> findAll() {
		Query query = entityManager.createQuery("from Employee");
		return query.getResultList();
	}

	public Employee findById(int id) {
		return entityManager.find(Employee.class, id);
	}

	public void deleteById(int id) {
		Employee employee = entityManager.find(Employee.class, id);
		if (employee != null) {
			entityManager.remove(employee);
		}
	}

	public Employee update(Employee employee) {
		return entityManager.merge(employee);
	}
}
