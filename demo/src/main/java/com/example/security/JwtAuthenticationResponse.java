package com.example.security;

import java.io.Serializable;

/**
 * 
 * @author Aquib
 * @version 1.0
 * @Date 06/06/2017
 *
 */
public class JwtAuthenticationResponse implements Serializable {

	private static final long serialVersionUID = 1250166508152483573L;

	private final String token;

	public JwtAuthenticationResponse(String token) {
		this.token = token;
	}

	public String getToken() {
		return this.token;
	}
}
