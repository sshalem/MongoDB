package com.example.exceptions;

public class CouponExpiredException extends RuntimeException {

	public CouponExpiredException(String message) {
		super(message);
	}

}
