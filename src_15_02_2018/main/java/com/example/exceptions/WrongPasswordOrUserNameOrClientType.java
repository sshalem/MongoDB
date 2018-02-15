package com.example.exceptions;

public class WrongPasswordOrUserNameOrClientType extends RuntimeException{
	
	public WrongPasswordOrUserNameOrClientType (String message) {
		super(message);
	}

}
