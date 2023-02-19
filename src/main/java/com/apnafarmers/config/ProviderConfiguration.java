package com.apnafarmers.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Data
@Component
@ToString
public class ProviderConfiguration {

	@Value("${email.host}")
	private String host;

	@Value("${email.port}")
	private Integer port;

	@Value("${email.username}")
	private String username;

	@Value("${email.password}")
	private String password;

	@Value("${email.debug}")
	private Boolean debug;

}
