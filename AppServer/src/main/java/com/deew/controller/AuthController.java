package com.deew.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.deew.AppServer.Exception.UserAlreadyExistException;
import com.deew.AppServer.modal.LoginRequest;
import com.deew.AppServer.modal.RegisterRequest;
import com.deew.service.AuthenticationService;
import com.deew.service.RegistrationService;

@RequestMapping("/auth")
@RestController
public class AuthController {

	@Autowired
	private AuthenticationService authService;
	
	@Autowired
	private RegistrationService registration;
	
	@RequestMapping(method=RequestMethod.POST,value="/authenticate")
	public ResponseEntity<String> authenticateUser(@RequestBody LoginRequest login) throws Exception {
		
		String token = authService.autheticateWithUserNameAndPassword(login.getUserName(), login.getPassword());
		return ResponseEntity.ok(token);
	}
	
	@RequestMapping(method=RequestMethod.POST,value="register")
	@ResponseStatus(HttpStatus.OK)
	public void register(@RequestBody RegisterRequest register) {
		try {
			registration.registerUser(register);
		}catch(UserAlreadyExistException uae) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Name Already Taken",null);
		}
		
	}
}
