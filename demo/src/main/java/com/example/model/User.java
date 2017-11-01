package com.example.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.security.Role;
import com.urbanairship.api.client.RequestErrorDetails.Location;

@Document(collection = "users")
public class User {

	@Id
	private String _id;

	private String password;

	private String fullName;

	private String email;

	private String username;

	private String token;

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "User [_id=" + this._id + ", password=" + this.password + ", fullName=" + this.fullName + ", email="
				+ this.email + ", username=" + this.username + ", token=" + this.token + "]";
	}

	public List<Role> getAuthorities() {

		return new ArrayList<Role>();
	}

}