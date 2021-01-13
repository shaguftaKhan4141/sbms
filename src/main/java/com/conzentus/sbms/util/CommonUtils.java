package com.conzentus.sbms.util;

import java.util.regex.Pattern;

public class CommonUtils {

	public static boolean exists(String value) {
		return value != null && !value.isEmpty();
	}

	public static String getNonNullable(String value) {
		return value != null && !value.isEmpty() ? value : "";
	}
	
	public static boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);
		if (email == null)
			return false;
		return pat.matcher(email).matches();
	}
}
