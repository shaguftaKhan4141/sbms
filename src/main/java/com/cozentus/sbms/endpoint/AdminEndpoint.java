package com.cozentus.sbms.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.cozentus.sbms.error.InvalidDataException;
import com.cozentus.sbms.error.NotFoundException;

public interface AdminEndpoint {

	@GetMapping(value = "/user/role/{roleId}")
	public ResponseEntity<?> getAllUsersByRoleId(@PathVariable("roleId") Long roleId) throws NotFoundException;

	@GetMapping(value = "/user")
	public ResponseEntity<?> getAllUsers(@RequestParam(name = "status", required = false) String requestStatus)
			throws NotFoundException, InvalidDataException;
	
}
