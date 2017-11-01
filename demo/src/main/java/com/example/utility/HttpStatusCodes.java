package com.example.utility;



public enum HttpStatusCodes {
	
	OK(200, "OK"),

	VALIDATION_ERROR(201, "Validation Error"),

	ALREADY_REGISTERED(202, "Already Registered (Active)"),
	UNAUTHORIZED(401,"");
	
	private final int value;

	private final String reasonPhrase;

	HttpStatusCodes(int value, String reasonPhrase) {
		this.value = value;
		this.reasonPhrase = reasonPhrase;
	}

	public int value() {
		return value;
	}

	public String reasonPhrase() {
		return reasonPhrase;
	}

	/**
	 * @param statusCode
	 * @return status
	 */
	public static HttpStatusCodes valueOf(int statusCode) {
		for (HttpStatusCodes status : values()) {
			if (status.value == statusCode) {
				return status;
			}
		}
		throw new IllegalArgumentException("No matching constant for [" + statusCode + "]");
	}
	
}
