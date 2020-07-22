package com.deew.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import com.deew.AppServer.authentication.TokenProcessor;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	private static Logger log =LoggerFactory.getLogger(AuthenticationServiceImpl.class);
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private UserDetailsService userDetailService;
	
	@Autowired
	private TokenProcessor tokenProcessor;
	@Override
	public String autheticateWithUserNameAndPassword(String userName, String password) throws Exception {
		log.info("UNAME={},PASS={}",userName,password);
		authenticate(userName, password);
		UserDetails userDetails = userDetailService.loadUserByUsername(userName);
		return tokenProcessor.generateToken(userDetails);
	}

	@Override
	public String authenticateWithToken(String token) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	private void authenticate(String userName, String password) {
		Authentication authentication=authManager.authenticate(new UsernamePasswordAuthenticationToken(userName, password));
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

}
