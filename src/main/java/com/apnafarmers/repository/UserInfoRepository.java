package com.apnafarmers.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apnafarmers.entity.UserInfo;

import java.util.Optional;

public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {
	Optional<UserInfo> findByName(String username);

}
