package com.travel.oAuth2.config.payload.response;

public class ApiResponse {

	private int status;
    private String message;
    
	public ApiResponse(String message, int status ) {
		super();
		this.status = status;
		this.message = message ;
	}

	public Integer getstatus() {
		return status;
	}

	public void setSuccess(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
