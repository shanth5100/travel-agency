package com.travel.oAuth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
		 http
//         .cors()
//             .and()
         .csrf()
             .disable()
         .sessionManagement()
             .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
             .and()
         .authorizeRequests()
                 .antMatchers("/api/auth/register") // to allow only registration 
                 .permitAll()
                 .antMatchers("/api/auth/login") // to allow only registration 
                 .permitAll()
//             .anyRequest()
//             .authenticated()
             ;
         
		 // Add our custom JWT security filter
//	      	http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class); // if not enabled user account loked exception will come
	}

}
