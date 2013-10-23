package com.exception;

public class WaitForElementException extends RuntimeException {
	private static final long serialVersionUID = 119788168751835050L;

	public WaitForElementException(String message) {
		super(message);
	}
}
