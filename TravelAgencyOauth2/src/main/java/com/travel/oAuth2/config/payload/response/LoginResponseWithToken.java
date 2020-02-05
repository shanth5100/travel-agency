package com.travel.oAuth2.config.payload.response;

public class LoginResponseWithToken {

	private String accessToken;
    private String refreshToken;
        
	public LoginResponseWithToken(String accessToken, String refreshToken) {
		super();
		this.accessToken = accessToken;
		this.refreshToken =refreshToken;
	}
	
	public String getAccessToken() {
		return accessToken;
	}
	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}
	public String getTokenType() {
		return refreshToken;
	}
	public void setTokenType(String refreshToken) {
		this.refreshToken = refreshToken;
	}

}
