package com.cozentus.sbms.error;

public class UserNotFoundException extends Exception {

	private static final long serialVersionUID = 2181174869331082282L;

	public UserNotFoundException(String message) {
		super(message);
	}
}
