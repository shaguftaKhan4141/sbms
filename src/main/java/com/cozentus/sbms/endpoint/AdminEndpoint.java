package com.cozentus.sbms.endpoint;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cozentus.sbms.error.InvalidDataException;
import com.cozentus.sbms.error.NotFoundException;
import com.cozentus.sbms.error.UserNotAuthorizedException;

@RequestMapping("/admin")
public interface AdminEndpoint {

	@GetMapping(value = "/user/role/{roleId}")
	public ResponseEntity<?> getAllUsersByRoleId(@PathVariable("roleId") Long roleId) throws NotFoundException;

	@GetMapping(value = "/user")
	public ResponseEntity<?> getAllUsers(@RequestParam(name = "status", required = false) String requestStatus)
			throws NotFoundException, InvalidDataException;
	
	@PutMapping(value = "/approve/user/{userId}")
	public ResponseEntity<?> approvedUserRequest(@PathVariable("userId") Long userId,
			@RequestParam("status") String requestStatus) throws NotFoundException, InvalidDataException;

	@PutMapping(value = "/approve/synopsis/{blogId}/{status}")
	ResponseEntity<?> approveSynopsis(@PathVariable("blogId") Long blogId, @PathVariable("status") String status)
			throws NotFoundException, UserNotAuthorizedException, InvalidDataException;	
}
