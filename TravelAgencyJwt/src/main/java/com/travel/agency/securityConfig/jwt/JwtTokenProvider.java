package com.travel.agency.securityConfig.jwt;

import java.util.Date;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenProvider {
	public String genarateToken(UserDetails userDetails) {
		Date now = new Date();
		
//		@Value("${app.jwtExpirationInMs}")
        long jwtExpirationInMs = 604800000;
		Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        
//		@Value("")
		String jwtSecret = "prashanth";
		return Jwts.builder()
				.signWith(SignatureAlgorithm.HS256, jwtSecret)
				.setSubject(userDetails.getUsername())
				.setExpiration(expiryDate)
				.setIssuedAt(new Date())
				.setIssuer(jwtSecret)
				.compact();
	}
}
