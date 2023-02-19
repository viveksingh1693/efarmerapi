package com.apnafarmers.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apnafarmers.entity.City;

@Repository
public interface CityRepository extends JpaRepository<City, Long> {

	List<City> findByNameStartsWithIgnoreCase(String startWith);

//	@Query("FROM Country n where n.name = ?1")
	List<City> findByNameStartsWithIgnoreCaseOrderByName(String rating, Pageable pg);

	City findByNameIgnoreCase(String name);
}
