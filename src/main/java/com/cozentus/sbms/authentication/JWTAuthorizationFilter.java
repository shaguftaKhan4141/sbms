package com.cozentus.sbms.authentication;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.cozentus.sbms.repository.BlogUserRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JWTAuthorizationFilter extends BasicAuthenticationFilter {
   
	private BlogUserRepository userRepository;
	private JwtDetails jwtDetails;

	public JWTAuthorizationFilter(AuthenticationManager authManager, BlogUserRepository userRepository
			, JwtDetails jwtDetails) {
		super(authManager);
		this.userRepository = userRepository;
		this.jwtDetails = jwtDetails;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		String header = req.getHeader(SecurityConstants.HEADER_STRING);

		if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(req, res);
			return;
		}

		UsernamePasswordAuthenticationToken authentication = parseToken(req);

		SecurityContextHolder.getContext().setAuthentication(authentication);
		chain.doFilter(req, res);
	}

	// Reads the JWT from the Authorization header, and then uses JWT to validate
	// the token
	private UsernamePasswordAuthenticationToken parseToken(HttpServletRequest request) {
		String token = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (token != null && token.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			String claims = token.replace(SecurityConstants.TOKEN_PREFIX, "");
			try {
				Jws<Claims> claimsJws = Jwts.parser().setSigningKey(jwtDetails.getJwtSecret().getBytes()).parseClaimsJws(claims);

				String username = claimsJws.getBody().getSubject();

				if ("".equals(username) || username == null) {
					return null;
				}

				String role = userRepository.findByUserName(username).getRole().getName();

				return new UsernamePasswordAuthenticationToken(username, null, List.of(new SimpleGrantedAuthority(role)));
			} catch (JwtException exception) {
				log.warn("Some exception : {} failed : {}", token, exception.getMessage());
			}
		}

		return null;
	}

}
