package com.deew.AppServer.modal;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LoginRequest implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8020802652955060646L;
	@JsonProperty("username")
	private String userName;
	@JsonProperty("password")
	private String password;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	

}
