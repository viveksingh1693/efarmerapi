package com.apnafarmers.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apnafarmers.entity.UserInfo;
import com.apnafarmers.repository.UserInfoRepository;

@Service
public class UserService {


	@Autowired
	private UserInfoRepository userRepository;

	public List<UserInfo> findAllUsers() {
		return this.userRepository.findAll();
	}

	public String findEmailByUsername(String username) {
		Optional<UserInfo> user = userRepository.findByName(username);
		if (user.isPresent()) {
			return user.get().getEmail();
		}
		return null;
	}

}
