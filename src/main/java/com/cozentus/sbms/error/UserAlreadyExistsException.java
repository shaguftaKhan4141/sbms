package com.cozentus.sbms.error;

public class UserAlreadyExistsException extends Exception{

	private static final long serialVersionUID = 2181174869331082282L;

	public UserAlreadyExistsException(String message) {

		super(message);
	}
}
