package com.apnafarmers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.apnafarmers.entity.Farmer;
import com.apnafarmers.repository.FarmerRepository;

@Component
public class FarmerServiceImpl implements FarmerService {

	@Autowired
	FarmerRepository farmerRepository;

	@Override
	public Farmer saveFarmer(Farmer farmer) {
		Farmer save = farmerRepository.save(farmer);
		return save;
	}

	@Override
	public List<Farmer> findAll() {
		List<Farmer> farmers = farmerRepository.findAll();
		return farmers;
	}

	@Override
	public List<Farmer> findByFirstNameStartsWithIgnoreCase(String startWith) {
		List<Farmer> farmers = farmerRepository.findByFirstNameStartsWithIgnoreCase(startWith);
		return farmers;
	}

	@Override
	public Optional<Farmer> findById(long id) {
		return farmerRepository.findById(id);
	}

	@Override
	public void deleteAll() {
		farmerRepository.deleteAll();
	}

	@Override
	public void deleteById(long id) {
		farmerRepository.deleteById(id);
	}

}
