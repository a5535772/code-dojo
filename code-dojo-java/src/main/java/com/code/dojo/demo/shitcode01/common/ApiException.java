package com.code.dojo.demo.shitcode01.common;

public class ApiException extends Exception {
	private static final long serialVersionUID = 1L;

	private String message;
	private String code;

	public ApiException(String code, String message) {
		this.code = code;
		this.message=message;
	}

	public String getMessage() {	
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
