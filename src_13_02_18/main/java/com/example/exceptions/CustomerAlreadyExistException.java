package com.example.exceptions;

public class CustomerAlreadyExistException extends RuntimeException {

	public CustomerAlreadyExistException(String message) {

		super(message);
	}

}
