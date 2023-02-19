package com.apnafarmers.service;

import java.util.List;
import java.util.Optional;

import com.apnafarmers.entity.Farmer;

public interface FarmerService {

	Farmer saveFarmer(Farmer farmer);

	List<Farmer> findAll();

	List<Farmer> findByFirstNameStartsWithIgnoreCase(String startWith);

	Optional<Farmer> findById(long id);

	void deleteAll();

	void deleteById(long id);

}
