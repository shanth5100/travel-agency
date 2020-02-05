package com.travel.oAuth2.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.travel.oAuth2.DAO.AppUserDao;
import com.travel.oAuth2.bean.AppUser;
import com.travel.oAuth2.config.jwt.JwtTokenProvider;
import com.travel.oAuth2.config.jwt.UserDetailsServiceImpl;
import com.travel.oAuth2.config.payload.request.SignUpRequest;
import com.travel.oAuth2.config.payload.response.ApiResponse;
import com.travel.oAuth2.config.payload.response.LoginResponseWithToken;
import com.travel.oAuth2.exception.ErrorHadling;

@Component
@RestController
@CrossOrigin
@RequestMapping("/api/auth/")
public class AppUserController {

	@Autowired
	AppUserDao appUserDao;
	
	@Autowired
	UserDetailsServiceImpl userSerImpl;
	
	@Autowired
	JwtTokenProvider tokenProvider;
	
//	@Autowired // if enable need some configuration
//	org.springframework.security.authentication.AuthenticationManager authManager;
	
	@PostMapping("register")
	private ResponseEntity<?> register(@Valid @RequestBody com.travel.oAuth2.config.payload.request.SignUpRequest signUpRequest) {
		if (appUserDao.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.ok(new ApiResponse("Email already exists", 400));
		}
		if (appUserDao.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.ok(new ApiResponse("Username already exists", 400));
		}
		
		AppUser user = getAppUser(signUpRequest);
		
		appUserDao.save(user);
		
		return ResponseEntity.ok(new ApiResponse("User registered succesfully", 200));
	}
	
	private AppUser getAppUser(SignUpRequest signUpRequest) {
		AppUser user = new AppUser();
		user.setEmail(signUpRequest.getEmail());
		user.setUsername(signUpRequest.getUsername());
		user.setPassword(signUpRequest.getPassword());
		return user;
	}

	@PostMapping("login")
	private ResponseEntity<?> login(@Valid @RequestBody com.travel.oAuth2.config.payload.request.LoginRequest loginRequest) throws ErrorHadling {
		
//		authenticate(loginRequest.getUsername(), loginRequest.getPassword());
		
		UserDetails userDetails= userSerImpl.loadUserByUsername(loginRequest.getUsername());
		
		String token = tokenProvider.genarateToken(userDetails);
		// find user with username
//		String loginToken = "User login token";
		String refresh = "User refresh token";
		return ResponseEntity.ok(new LoginResponseWithToken(token, refresh));
	}

//	private void authenticate(String username, String password) throws ErrorHadling {
//		try {
//			Authentication authentication = authManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
//			SecurityContextHolder.getContext().setAuthentication(authentication);
//		} catch (DisabledException e) {
//			throw new ErrorHadling(403, "USER_DISABLED");
//		} catch (BadCredentialsException e) {
//			throw new ErrorHadling(300, "INVALID_CREDENTIALS");
//		}
//	}
	
}
