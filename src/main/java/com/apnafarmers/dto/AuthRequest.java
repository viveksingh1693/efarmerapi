package com.apnafarmers.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthRequest {

	@Pattern(regexp = "^(?=.{1,50}$)(?![_.])(?!.*[_.]{2})[a-zA-Z0-9._]+(?<![_.])$")
	@NotNull
	@Size(min = 1, max = 50)
	private String username;

	@NotNull
	@Size(min = 4, max = 32)
	private String password;

	private Boolean rememberMe;
}
