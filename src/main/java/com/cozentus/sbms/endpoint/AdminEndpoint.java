package com.cozentus.sbms.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.cozentus.sbms.error.UserNotFoundException;

public interface AdminEndpoint {

	@GetMapping(value = "/user/role/{roleId}")
	public ResponseEntity<?> getAllUsersByRole(@PathVariable("roleId") Long roleId) throws UserNotFoundException;
}
