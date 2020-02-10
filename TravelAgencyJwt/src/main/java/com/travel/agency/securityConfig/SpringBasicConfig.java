package com.travel.agency.securityConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.travel.agency.securityConfig.userDetailsService.UserDetailsServiceIMpl;

@Configuration
@EnableWebSecurity
public class SpringBasicConfig extends WebSecurityConfigurerAdapter{

	
	UserDetailsServiceIMpl userDetailsService; 
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		.authorizeRequests()
		.antMatchers("/travel/auth/register").permitAll()
		.antMatchers("/travel/auth/login").permitAll()
		.anyRequest().authenticated();
	}
	 @Autowired
	 public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
//	     auth.userDetailsService(userDetailsService)
//	             .passwordEncoder(encoder());
//		 auth.inMemoryAuthentication();
		 auth.authenticationProvider(authenticationProvider());
	 }
	 
	 private DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authenticationProvider =  new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(encoder());
		return authenticationProvider;
	 }

//	 @Override
	 @Bean
	 public AuthenticationManager authenticationManagerBean() throws Exception {
	     return super.authenticationManagerBean();
	 }
	private PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
