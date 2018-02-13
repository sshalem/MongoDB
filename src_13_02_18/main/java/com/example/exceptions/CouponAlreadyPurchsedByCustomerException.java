package com.example.exceptions;

public class CouponAlreadyPurchsedByCustomerException extends RuntimeException {

	public CouponAlreadyPurchsedByCustomerException(String message) {
		super(message);
	}

}
