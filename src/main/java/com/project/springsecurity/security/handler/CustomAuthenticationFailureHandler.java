package com.project.springsecurity.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
		AuthenticationException exception) throws IOException, ServletException {
		String errorMessage = "Invalid Username or Password";

		if(exception instanceof BadCredentialsException){
			errorMessage = "Invalid Username or Password";
		} else if(exception instanceof InsufficientAuthenticationException){
			errorMessage = "Invalid SecretKey";
		}

		setDefaultFailureUrl("/login?error=true&exception=" + exception.getMessage());

		super.onAuthenticationFailure(request, response, exception);
	}
}
