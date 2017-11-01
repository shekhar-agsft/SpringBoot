package com.example.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "login Request Body.")
public class LoginDetails {

	@ApiModelProperty(name = "username")
	private String userName;
	@ApiModelProperty(name = "password")
	private String password;

	public LoginDetails(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}

	public LoginDetails() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "LoginDetails [userName=" + this.userName + ", password=" + this.password + "]";
	}

}
