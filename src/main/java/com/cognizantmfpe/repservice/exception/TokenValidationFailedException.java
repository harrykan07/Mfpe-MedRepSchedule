package com.cognizantmfpe.repservice.exception;

public class TokenValidationFailedException extends Exception {
	private static final long serialVersionUID = 1L;

	public TokenValidationFailedException(String message) {
		super(message);
	}
}