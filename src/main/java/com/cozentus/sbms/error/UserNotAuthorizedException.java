package com.cozentus.sbms.error;

public class UserNotAuthorizedException extends Exception{

	private static final long serialVersionUID = 1054164202509595176L;

	public UserNotAuthorizedException() {

		super("User not authorized to perform this action");
	}

}
