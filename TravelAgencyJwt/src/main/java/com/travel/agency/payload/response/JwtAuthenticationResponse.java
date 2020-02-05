package com.travel.agency.payload.response;

public class JwtAuthenticationResponse {

	private String accessToken;
	private String refreshToken;
        
	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}

	public JwtAuthenticationResponse(String accessToken) {
		super();
		this.accessToken = accessToken;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

}
