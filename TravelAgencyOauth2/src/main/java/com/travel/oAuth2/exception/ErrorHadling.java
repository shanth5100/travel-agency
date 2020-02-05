package com.travel.oAuth2.exception;

public class ErrorHadling extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int code;
    private String message;
    
	public ErrorHadling(int code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
     
}
