package com.travel.agency.payload.response.error;

public class ApiResponse {
	private int code;
    private String message;
    
	public ApiResponse(String message, int code) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getcode() {
		return code;
	}

	public void setcode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
