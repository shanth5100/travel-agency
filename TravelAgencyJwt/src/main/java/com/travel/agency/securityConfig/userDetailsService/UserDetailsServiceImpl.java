package com.travel.agency.securityConfig.userDetailsService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.travel.agency.DAO.UserRepository;
import com.travel.agency.bean.AppUser;

public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = userRepository.findUserByUsername(username);
		
		if (user == null) {
			throw new UsernameNotFoundException("username not found "+username);
		}
		
		User springUser = new User(user.getUsername(), user.getPassword(), new ArrayList<>());
		
		return springUser;
	}

}
