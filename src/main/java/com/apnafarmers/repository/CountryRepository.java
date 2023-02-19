package com.apnafarmers.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apnafarmers.entity.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

	List<Country> findByNameStartsWithIgnoreCase(String startWith);

//	@Query("FROM Country n where n.name = ?1")
	List<Country> findByNameStartsWithIgnoreCaseOrderByName(String rating, Pageable pg);

	Country findByNameIgnoreCase(String name);
}
