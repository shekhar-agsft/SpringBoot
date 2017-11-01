package com.example.model;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Register Request Body.")
public class RegisterDTO {

	@Valid
	@NotNull(message = "Full Name cannot be null")
	@Size(min = 0, max = 50, message = "First Name should be between 1 to 50 characters")
	@ApiModelProperty(name = "full_name")
	String full_name;

	@Valid
	@NotNull(message = "Username cannot be null")
	@Size(min = 0, max = 50, message = "Username should be between 1 to 50 characters")
	@ApiModelProperty(name = "username")
	String username;

	@Valid
	@NotNull(message = "Email cannot be null")
	@ApiModelProperty(name = "email")
	String email;

	@Valid
	@NotNull(message = "Password cannot be null")
	@Size(min = 8, max = 50, message = "Password should be between 8 to 50 characters")
	@ApiModelProperty(name = "password")
	String password;

	private RegisterDTO() {
	}

	public String getFull_name() {
		return full_name;
	}

	public void setFull_name(String full_name) {
		this.full_name = full_name;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "RegisterDTO [full_name=" + full_name + ", username=" + username + ", email=" + email + ", password="
				+ password + "]";
	}

}
