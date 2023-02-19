package com.apnafarmers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apnafarmers.entity.Crop;
import com.apnafarmers.repository.CropRepository;

@Service
public class CropServiceImpl implements CropService {

	@Autowired
	CropRepository cropRepository;

	@Override
	public Crop saveFarmer(Crop farmer) {
		return cropRepository.save(farmer);
	}

	@Override
	public List<Crop> findAll() {
		return cropRepository.findAll();
	}

	@Override
	public List<Crop> findByNameStartsWithIgnoreCase(String startWith) {
		return cropRepository.findByNameStartsWithIgnoreCase(startWith);
	}

	@Override
	public Optional<Crop> findById(long id) {
		return cropRepository.findById(id);
	}

	@Override
	public void deleteAll() {
		cropRepository.deleteAll();
	}

	@Override
	public void deleteById(long id) {
		cropRepository.deleteById(id);
	}

}
