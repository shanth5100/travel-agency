package com.travel.oAuth2.config.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;



@Component
public class JwtTokenProvider {
//	private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);
	
    @Value("${app.jwtSecret}")
    private String jwtSecret;

    @Value("${app.jwtExpirationInMs}")
    private int jwtExpirationInMs;

	public String genarateToken(UserDetails userDetails) {
		Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);
        
		return Jwts.builder()
				.signWith(SignatureAlgorithm.HS256, jwtSecret)
				.setSubject(userDetails.getUsername())
				.setExpiration(expiryDate)
				.setIssuedAt(new Date())
				.setIssuer("prashanth")
				.compact();
	}
    
}
