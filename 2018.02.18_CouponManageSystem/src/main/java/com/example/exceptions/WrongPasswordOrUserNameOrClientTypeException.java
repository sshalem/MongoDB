package com.example.exceptions;

public class WrongPasswordOrUserNameOrClientTypeException extends RuntimeException{
	
	public WrongPasswordOrUserNameOrClientTypeException (String message) {
		super(message);
	}

}
