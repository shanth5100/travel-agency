package com.travel.agency.payload.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SignUpRequest {
	
	@NotBlank
    @Size(min = 6, max = 15)
	private String username;
	
	@NotBlank
    @Size(min = 6, max = 20)
	private String password;
	
	@NotBlank
	@Email
	private String email;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}
