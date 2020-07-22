package com.deew.AppServer.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class AppAuthEntryPoint implements AuthenticationEntryPoint  {
	Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		logger.info("AuthEntryPoint , request={}",request.getRequestURI());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Not allowed to access path "+request.getRequestURI());
	}
}
