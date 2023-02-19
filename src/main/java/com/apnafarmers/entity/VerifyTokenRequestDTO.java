package com.apnafarmers.entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyTokenRequestDTO {

	@NotNull
	private String username;

	@NotNull
	private Integer otp;

	private Boolean rememberMe;

}
