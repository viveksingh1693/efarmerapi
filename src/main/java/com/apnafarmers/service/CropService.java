package com.apnafarmers.service;

import java.util.List;
import java.util.Optional;

import com.apnafarmers.entity.Crop;

public interface CropService {

	
	Crop saveFarmer(Crop farmer);

	List<Crop> findAll();

	List<Crop> findByNameStartsWithIgnoreCase(String startWith);

	Optional<Crop> findById(long id);

	void deleteAll();

	void deleteById(long id);


}
