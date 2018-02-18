package com.example.exceptions;

public class CouponAlreadyExistInCompanyDBException extends RuntimeException {

	public CouponAlreadyExistInCompanyDBException(String message) {

		super(message);
	}

}
