package com.apnafarmers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apnafarmers.dto.AuthRequest;
import com.apnafarmers.entity.UserInfo;
import com.apnafarmers.entity.VerifyTokenRequestDTO;
import com.apnafarmers.service.OtpService;
import com.apnafarmers.service.ProductService;
import com.apnafarmers.service.TokenProvider;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/farmers")
public class AuthenticationController {

	@Autowired
	private ProductService service;

	@Autowired
	private TokenProvider tokenProvider;

	@Autowired
	private OtpService otpService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome this endpoint is not secure";
	}

	@PostMapping("/new")
	public String addNewUser(@RequestBody UserInfo userInfo) {
		return service.addUser(userInfo);
	}

	@PostMapping(value = "/authenticate")
	public ResponseEntity<String> authorize(@Valid @RequestBody AuthRequest authRequest) {
		log.info("Credentials {}", authRequest);

		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

			String token = tokenProvider.createToken(authentication, authRequest.getRememberMe());

			SecurityContextHolder.getContext().setAuthentication(authentication);
			log.info("Completed !!!!!!!!");

			if (token == null) {
				token = "OTP Send to Mail.";
			}

			return new ResponseEntity<>(token, HttpStatus.OK);
		} catch (AuthenticationException exception) {
			log.info("{}", exception);
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
	}

	@PostMapping(value = "/authorize")
	public ResponseEntity<String> verifyOtp(@Valid @RequestBody VerifyTokenRequestDTO verifyTokenRequest) {
		log.info("Inside VerifyOtp");
		String username = verifyTokenRequest.getUsername();
		Integer otp = verifyTokenRequest.getOtp();
		Boolean rememberMe = verifyTokenRequest.getRememberMe();

		boolean isOtpValid = otpService.validateOTP(username, otp);
		if (!isOtpValid) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

		String token = tokenProvider.createTokenAfterVerifiedOtp(username, rememberMe);

		log.info("Inside VerifyOtp End");

		return new ResponseEntity<>(token, HttpStatus.OK);
	}

}
