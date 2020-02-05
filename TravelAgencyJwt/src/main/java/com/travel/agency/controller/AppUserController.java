package com.travel.agency.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.travel.agency.DAO.UserRepository;
import com.travel.agency.bean.AppUser;
import com.travel.agency.config.security.jwt.JwtTokenProvider;
import com.travel.agency.config.security.service.CustomUserDetailsService;
import com.travel.agency.payload.request.LoginRequest;
import com.travel.agency.payload.request.SignUpRequest;
import com.travel.agency.payload.response.JwtAuthenticationResponse;
import com.travel.agency.payload.response.error.ApiResponse;

@RestController
@RequestMapping("/api/auth")
public class AppUserController {
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JwtTokenProvider jwtTokenProvider;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	CustomUserDetailsService userDetailService;
	
	@PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> register(@Valid @RequestBody SignUpRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<Object>(new ApiResponse(false, "Username is already taken!"), HttpStatus.BAD_REQUEST);
		} 
		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return new ResponseEntity<Object>(new ApiResponse(false, "Email address already in use!"), HttpStatus.BAD_REQUEST);
		} 
		
		AppUser user = new AppUser();
		
		user.setUsername(signUpRequest.getUsername());
//		user.setPassword(signUpRequest.getPassword());
		user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
		user.setEmail(signUpRequest.getEmail());
		
		AppUser appUser = userRepository.save(user);
				
		URI location = ServletUriComponentsBuilder
				.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(appUser.getUsername()).toUri();
		
		return ResponseEntity.created(location).body(new ApiResponse(true, "User registered successfully"));
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
		
		Authentication authentication = authenticationManager
				.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
				);
//		
		SecurityContextHolder.getContext().setAuthentication(authentication); // set the logged in status
		
		UserDetails userDetails = userDetailService.loadUserByUsername(loginRequest.getUsername());
		
		String jwtToken = jwtTokenProvider.generateToken(userDetails);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwtToken));
//		
//		if (userRepository.existsByEmail(loginRequest.getUsername())) {
//			AppUser appUser= null;
//			try {
//				appUser = userRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
//				System.out.println(appUser);
//				if (appUser != null) {
////					String s = jwtTokenProvider.generateToken(authentication)
//					return ResponseEntity.ok(new JwtAuthenticationResponse("jwtToken"));
//				} else {
//				}
//				
//				return ResponseEntity.ok(new JwtAuthenticationResponse("jwtToken"));
//			} catch (Exception e) {
//				return ResponseEntity.ok(new ApiResponse(false, "Bad credentials"));
//			}
//			
//		} 
//		return ResponseEntity.ok(new ApiResponse(false, "Bad credentials"));
//		return ResponseEntity.ok(new JwtAuthenticationResponse("jwtToken"));
	}
	
}
