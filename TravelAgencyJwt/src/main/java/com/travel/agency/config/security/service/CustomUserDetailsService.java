package com.travel.agency.config.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.travel.agency.DAO.UserRepository;
import com.travel.agency.bean.AppUser;

@Service
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
		AppUser user = userRepository.findByUsername(usernameOrEmail)
			.orElseThrow(()->new UsernameNotFoundException("User not found with : "+usernameOrEmail));
		System.out.println(user);
		return UserPrincipal.getUserDetails(user);
	}

	// This method is used by JWTAuthenticationFilter
	@Transactional
	public UserDetails loadUserById(Long id) {
		AppUser user =  userRepository.findById(id)
				.orElseThrow(()->new UsernameNotFoundException("User not found with id : " + id));
		return UserPrincipal.getUserDetails(user);
	}
}
