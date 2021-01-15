package com.cozentus.sbms.authentication;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
public class JwtDetails {

	@Value("${jwt.secret}")
    private String jwtAudience;
	
	@Value("${jwt.issuer}")
    private String jwtIssuer;
	
	@Value("${jwt.secret}")
    private String jwtSecret;
	
	@Value("${jwt.type}")
    private String jwtType;
	
}
