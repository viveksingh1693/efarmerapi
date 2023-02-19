package com.apnafarmers.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apnafarmers.entity.Crop;

@Repository
public interface CropRepository extends JpaRepository<Crop, Long> {

	List<Crop> findByNameStartsWithIgnoreCase(String startWith);

	List<Crop> findByNameStartsWithIgnoreCaseOrderByName(String rating, Pageable pg);

	Crop findByNameIgnoreCase(String name);
}
