package com.apnafarmers.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.apnafarmers.dto.Product;
import com.apnafarmers.entity.UserInfo;
import com.apnafarmers.repository.UserInfoRepository;

@Service
public class ProductService {

	List<Product> productList = null;

	@Autowired
	private UserInfoRepository repository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	public String addUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		repository.save(userInfo);
		return "user added to system ";
	}
}
