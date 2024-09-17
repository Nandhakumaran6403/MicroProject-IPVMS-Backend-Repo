package com.visitor.application.repository;

import java.util.List;

import com.visitor.application.model.AdministrativeUser;

public interface AdministrativeUserRepository {

	AdministrativeUser save(AdministrativeUser administrativeUser);

	List<AdministrativeUser> findAll();

	AdministrativeUser findById(int id);

	void deleteById(int id);

	AdministrativeUser update(AdministrativeUser administrativeUser);
}
