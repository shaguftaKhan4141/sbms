package com.cozentus.sbms.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.cozentus.sbms.enumeration.Role;

@Component
public class AuthenticationFacade implements IAuthenticationFacade {

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }
    
    @Override
    public String getUserName() {
    	return getAuthentication().getName();
    }
    
    @Override
    public boolean hasAuthority(Role authority) {
    	return getAuthentication().getAuthorities()
    			.stream()
    			.anyMatch(grantedAuthority -> authority.toString().equals(grantedAuthority.getAuthority()));
    }
}