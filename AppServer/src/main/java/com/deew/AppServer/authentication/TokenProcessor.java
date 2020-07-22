package com.deew.AppServer.authentication;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenProcessor {

	public String generateToken(UserDetails userDetails);
	public boolean validateToken(String token, UserDetails userDetail) throws Exception;
	public String getUserName(String token) throws Exception;
}
