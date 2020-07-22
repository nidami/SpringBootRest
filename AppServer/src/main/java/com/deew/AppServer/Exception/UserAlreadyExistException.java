package com.deew.AppServer.Exception;

public class UserAlreadyExistException extends RuntimeException{

	private String message;

	public UserAlreadyExistException(String message) {
		this.message = message;
	}
	
}
