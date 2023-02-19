package com.apnafarmers.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.apnafarmers.entity.Farmer;

public interface FarmerRepository extends JpaRepository<Farmer, Long> {

	List<Farmer> findByFirstNameStartsWithIgnoreCase(String startWith);

	List<Farmer> findByFirstNameStartsWithIgnoreCaseOrderByFirstName(String rating, Pageable pg);

	Farmer findByFirstNameIgnoreCase(String name);

}
