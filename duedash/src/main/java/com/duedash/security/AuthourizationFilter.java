package com.duedash.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AuthourizationFilter extends BasicAuthenticationFilter{

	public AuthourizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
		// TODO Auto-generated constructor stub
	}

	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain, ServletRequest requset)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		super.doFilterInternal(request, response, chain);
		String header = request.getHeader(SecurityConstants.HEADER_STRING);
		
		 if (header == null || !header.startsWith(SecurityConstants.TOKEN_PREFIX)) {
	            chain.doFilter(request, response);
	            return;
	        }
	        
	        UsernamePasswordAuthenticationToken authentication = getAuthentication(request);
	        SecurityContextHolder.getContext().setAuthentication(authentication);
	        chain.doFilter(requset, response);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request) {
		 String token = request.getHeader(SecurityConstants.HEADER_STRING);
	        
	        if (token != null) {
	            
	            token = token.replace(SecurityConstants.TOKEN_PREFIX, "");
	            
	            String user = Jwts.parser()
	                    .setSigningKey( SecurityConstants.TOKEN_SECRET )
	                    .parseClaimsJws( token )
	                    .getBody()
	                    .getSubject();
	            
	            if (user != null) {
	                return new UsernamePasswordAuthenticationToken(user, null, new ArrayList<>());
	            }
	            
	            return null;
	        }
	        
	        return null;
		
		
	}
}
