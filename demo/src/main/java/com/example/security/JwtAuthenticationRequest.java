package com.example.security;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "username", "password" })
public class JwtAuthenticationRequest implements Serializable {

	private static final long serialVersionUID = -8445943548965154778L;
	
	@NotNull
	// @Size(min = 2, max = 100)
	private String username;

	@NotNull
	// @Size(min = 8, max = 50)
	private String password;

	public JwtAuthenticationRequest() {
		super();
	}

	public JwtAuthenticationRequest(String username, String password) {
		this.setUsername(username);
		this.setPassword(password);
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
