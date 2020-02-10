package com.travel.agency.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.travel.agency.DAO.UserRepository;
import com.travel.agency.bean.AppUser;
import com.travel.agency.payload.request.LoginRequest;
import com.travel.agency.payload.request.SignUpRequest;
import com.travel.agency.payload.response.error.ApiResponse;

@RestController
@RequestMapping("/travel/auth/")
public class AuthController {

	@Autowired
	UserRepository userRepository;
	
	private AppUser converSignupToAppUser(@Valid SignUpRequest signup) {
		
		AppUser appUser = new AppUser();
		
		appUser.setEmail(signup.getPassword());
		appUser.setUsername(signup.getUsername());
		appUser.setPassword(signup.getPassword());
		
		return appUser;
	}

	@PostMapping("register")
	public ResponseEntity<?> register(@Valid @RequestBody SignUpRequest signup) {
		if (userRepository.existsByEmail(signup.getEmail())) {
			return ResponseEntity.ok(new ApiResponse("Email already exists", 400));
		}
		if (userRepository.existsByUsername(signup.getUsername())) {
			return ResponseEntity.ok(new ApiResponse("Username already exists", 400));
		}
		AppUser appUser = converSignupToAppUser(signup);
			userRepository.save(appUser);
		
		return ResponseEntity.ok(new ApiResponse("user registered succesfully", 201));
	}

	@PostMapping("login")
	@ResponseStatus(value = HttpStatus.OK)
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest login) {
		
		return ResponseEntity.ok(new ApiResponse("user logged-in succesfully", 202));
	}
	
}
