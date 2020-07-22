package com.deew.service;

public interface AuthenticationService {
	public String autheticateWithUserNameAndPassword(String userName, String password) throws Exception;
	public String authenticateWithToken(String token) throws Exception;

}
