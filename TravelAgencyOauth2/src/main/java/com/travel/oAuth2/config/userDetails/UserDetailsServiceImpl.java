package com.travel.oAuth2.config.userDetails;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.travel.oAuth2.DAO.AppUserDao;
import com.travel.oAuth2.bean.AppUser;


@Component
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	UserDetails userDetails;// = new User(null, null, false, false, false, false, null);
	
	@Autowired
	AppUserDao appUserDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user = appUserDao.findByUsername(username);

		if (user == null) {
			throw new UsernameNotFoundException("Username not found by " + username);
		}
		
		UserDetails secureUser = new User(user.getUsername(), user.getPassword(), new ArrayList<>());
		return secureUser;
	}

}
