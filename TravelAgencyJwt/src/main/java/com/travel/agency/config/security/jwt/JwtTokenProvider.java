package com.travel.agency.config.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtTokenProvider {
	 private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

	    @Value("${app.jwtSecret}")
	    private String jwtSecret;

	    @Value("${app.jwtExpirationInMs}")
	    private int jwtExpirationInMs;

	    public String generateToken(Authentication authentication) {

//	        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
	    	System.out.println("Generating token");
	        Date now = new Date();
	        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

	        return Jwts.builder()
	                //.setSubject(Long.toString(userPrincipal.getId()))
	                .setIssuedAt(new Date())
	                .setExpiration(expiryDate)
	                .signWith(SignatureAlgorithm.HS512, jwtSecret)
	                .compact();
	    }
	    
	    public String generateToken(UserDetails userDetails) {

//	        UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
	    	System.out.println("Generating token");
	        Date now = new Date();
	        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

	        return Jwts.builder()
	                .setSubject(userDetails.getUsername())
	                .setIssuedAt(new Date())
	                .setExpiration(expiryDate)
	                .signWith(SignatureAlgorithm.HS512, jwtSecret)
	                .compact();
	    }

	    public Long getUserIdFromJWT(String token) {
	        Claims claims = Jwts.parser()
	                .setSigningKey(jwtSecret)
	                .parseClaimsJws(token)
	                .getBody();

	        return Long.parseLong(claims.getSubject());
	    }

	    public boolean validateToken(String authToken) {
	    	System.out.println(authToken);
	        try {
	        	System.out.println(jwtSecret);
	        	Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
//	        	System.out.println("Claims "+ claims);
	            System.out.println("efiuwegfiuer");
	            return true;
	        } catch (SignatureException ex) {
	            logger.error("Invalid JWT signature");
	        } catch (MalformedJwtException ex) {
	            logger.error("Invalid JWT token");
	        } catch (ExpiredJwtException ex) {
	            logger.error("Expired JWT token");
	        } catch (UnsupportedJwtException ex) {
	            logger.error("Unsupported JWT token");
	        } catch (IllegalArgumentException ex) {
	            logger.error("JWT claims string is empty.");
	        }
	        return false;
	    }
}

