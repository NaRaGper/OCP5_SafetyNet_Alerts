package com.nathan.safetynetalerts.error;

public class RequestError {
	private String message;
	private String description;
	
	public RequestError(String message) {
		super();
		this.message = message;
	}
	
	public RequestError(String message, String description) {
		super();
		this.message = message;
		this.description = description;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
