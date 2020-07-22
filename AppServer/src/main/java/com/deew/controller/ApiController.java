package com.deew.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ApiController {

	@RequestMapping("/ping")
	public String ping() {
	   return "pong";	
	}
	
	@RequestMapping("/me")
	public String getUserName() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	   System.out.println(authentication.getName());
		return null;
	}
	
}
