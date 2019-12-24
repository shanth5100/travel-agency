package com.travel.agency.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.travel.agency.config.security.jwt.JwtAuthenticationEntryPoint;
import com.travel.agency.config.security.jwt.JwtAuthenticationFilter;
//http:www.appsdeveloperblog.com/user-authentication-spring-boot-spring-security-jwt/
import com.travel.agency.config.security.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomUserDetailsService customUserDetailsService;
	
	 @Autowired
	 private JwtAuthenticationEntryPoint unauthorizedHandler;
		
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
//        auth.userDetailsService(userDetailsService())
//        .passwordEncoder(passwordEncoder());
    }
	
	@Bean
	 @Override
	protected UserDetailsService userDetailsService() {
		return super.userDetailsService();
	}

	@Bean
    public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
    }

	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		System.out.println("configure(HttpSecurity http)");
//		http
//			.authorizeRequests()
//			.antMatchers("/oauth/user/register").permitAll()
//			.antMatchers("/address/").permitAll();
//		
//		http.csrf().disable();
		
		 http
         .cors()
             .and()
         .csrf()
             .disable()
         .exceptionHandling()
             .authenticationEntryPoint(unauthorizedHandler)
             .and()
         .sessionManagement()
             .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
             .and()
         .authorizeRequests()
             .antMatchers("/",
                 "/favicon.ico",
                 "/**/*.png",
                 "/**/*.gif",
                 "/**/*.svg",
                 "/**/*.jpg",
                 "/**/*.html",
                 "/**/*.css",
                 "/**/*.js")
                 .permitAll()
                 .antMatchers("/api/auth/**")
//                 .antMatchers("/api/auth/register") // to allow only registration 
                 .permitAll()
             .anyRequest()
             .authenticated()
//             .and() // from here Adding custom filter for Accessing the Data
             ;
         
		 // Add our custom JWT security filter
	      	http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	}

	@Bean
	JwtAuthenticationFilter jwtAuthenticationFilter() { // Should not be private, final 
		System.out.println("JwtAuthenticationFilter jwtAuthenticationFilter");
		return new JwtAuthenticationFilter();
	}
	
	
//	@Override some times get the Error .hasRole("user")
//    public void configure(WebSecurity web) { // ignoring any request 
////		web.ignoring().antMatchers("/address/");
//		web.ignoring().antMatchers("/oauth/user/register");
//	}
	
}
