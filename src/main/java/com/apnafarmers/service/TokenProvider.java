package com.apnafarmers.service;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.apnafarmers.entity.UserInfo;
import com.apnafarmers.repository.UserInfoRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.persistence.EntityNotFoundException;

@Component
public class TokenProvider {

	@Value("${jwt.secret}")
	private String secretKey;

	@Value("${jwt.expiration}")
	private long tokenValidityInSeconds;

	@Value("${jwt.expiration}")
	private long tokenValidityInSecondsForRememberMe;

	@Autowired
	private OtpService otpService;

	@Autowired
	UserInfoRepository userRepository;

	public String createToken(Authentication authentication, Boolean rememberMe) {
		String username = authentication.getName();
		UserInfo user = userRepository.findByName(username)
				.orElseThrow(() -> new EntityNotFoundException("User with username " + username + " not found!"));

		if (user.getIsOtpRequired()) {
			otpService.generateOtp(user.getName());
			return null;
		}
		return generateToken(authentication, rememberMe);
	}

	public String createTokenAfterVerifiedOtp(String username, Boolean rememberMe) {
		UserInfo user = userRepository.findByName(username)
				.orElseThrow(() -> new EntityNotFoundException("User not found!"));

		String roles = user.getRoles();
		String[] split = roles.split(",");

		List<GrantedAuthority> authorities = Arrays.asList(split).stream().map(role -> new SimpleGrantedAuthority(role))
				.collect(Collectors.toList());

		Authentication authentication = new UsernamePasswordAuthenticationToken(user.getName(), user.getPassword(),
				authorities);

		return generateToken(authentication, rememberMe);
	}

	private String generateToken(Authentication authentication, Boolean rememberMe) {

//		String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority)
//				.collect(Collectors.joining(","));

		long now = new Date().getTime();
		Date validity = new Date(System.currentTimeMillis() + 1000 * 60 * 30);
		if (Boolean.TRUE.equals(rememberMe)) {
			validity = new Date(now + this.tokenValidityInSecondsForRememberMe * 1000);
		} else {
			validity = new Date(now + this.tokenValidityInSeconds * 1000);
		}

		Map<String, Object> claims = new HashMap<>();
		// @formatter:off
				String token = Jwts.builder()
		        .setClaims(claims)
		        .setSubject(authentication.getName())
		        .setIssuedAt(new Date(System.currentTimeMillis()))
		        .setExpiration(validity)
		        .signWith(getSignKey(), SignatureAlgorithm.HS256).compact();
				//.signWith(SignatureAlgorithm.HS512, secretKey)
		// @formatter:on

		return token;

	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	private Claims extractAllClaims(String token) {
		// @formatter:off
        return Jwts
                .parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
     // @formatter:on
	}

	private Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}

	private Key getSignKey() {
		byte[] keyBytes = Decoders.BASE64.decode(secretKey);
		return Keys.hmacShaKeyFor(keyBytes);
	}
}
