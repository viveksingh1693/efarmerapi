package com.apnafarmers.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apnafarmers.entity.Media;

@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {

//	List<Media> findByNameStartsWithIgnoreCase(String startWith);
//
////	@Query("FROM Country n where n.name = ?1")
//	List<Media> findByNameStartsWithIgnoreCaseOrderByName(String rating, Pageable pg);
//
//	Media findByNameIgnoreCase(String name);
}
