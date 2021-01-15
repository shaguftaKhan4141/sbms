package com.cozentus.sbms.authentication;

import org.springframework.security.core.Authentication;

import com.cozentus.sbms.enumeration.Role;

public interface IAuthenticationFacade {
	public Authentication getAuthentication();
    public String getUserName();
    public boolean hasAuthority(Role authority);
}
