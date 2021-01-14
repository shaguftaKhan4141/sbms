package com.conzentus.sbms.authentication;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.conzentus.sbms.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
     
	private AuthenticationManager authenticationManager;
    private JwtDetails jwtDetails;
    
	 public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JwtDetails jwtDetails) {
	        this.authenticationManager = authenticationManager;
	        this.jwtDetails = jwtDetails;
	        //setFilterProcessesUrl("/user/login/token"); 
	    }
	 
	 @Override
	 public Authentication attemptAuthentication(HttpServletRequest req,
	                                                HttpServletResponse res) throws AuthenticationException {
		 try {
			 User creds = new ObjectMapper()
	                    .readValue(req.getInputStream(), User.class);

	            return authenticationManager.authenticate(
	                    new UsernamePasswordAuthenticationToken(
	                            creds.getUserName(),
	                            creds.getPassword(),
	                            new ArrayList<>())
	            );
	        } catch (IOException e) {
	            throw new RuntimeException(e);
	        }
	 }
	 
	   @Override
	    protected void successfulAuthentication(HttpServletRequest req,
	                                            HttpServletResponse response,
	                                            FilterChain chain,
	                                            Authentication auth) throws IOException {
		   
		    MyUserDetails user = (MyUserDetails)auth.getPrincipal();
		   
		    SecretKey secretKey = Keys.hmacShaKeyFor(jwtDetails.getJwtSecret().getBytes());
		    String token = Jwts.builder()
		            .signWith(secretKey, SignatureAlgorithm.HS256)
		            .setHeaderParam("typ", jwtDetails.getJwtType())
		            .setIssuer(jwtDetails.getJwtIssuer())
		            .setAudience(jwtDetails.getJwtAudience())
		            .setSubject(user.getUsername())
		            .setExpiration(new Date(System.currentTimeMillis() + 864000000))
		            .compact();
		    response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		   
	    }
	 
}
