package com.deew.AppServer.authentication;

import static com.deew.common.AppConstants.AUTH_HEADER;
import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.deew.service.UserDetailServiceImpl;
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

	private static final Logger logger= LoggerFactory.getLogger(JwtAuthFilter.class);
	
	@Autowired
	private UserDetailsService userDetailService;

	@Autowired
	private TokenProcessor tokenProcessor;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		 String userName =null;
		 String authHeader=request.getHeader(AUTH_HEADER);
		 logger.info("AuthHeader ={}",authHeader);
		 if(authHeader!=null && authHeader.startsWith("bearer")) {
			 String token = authHeader.substring(7);
			 try {
				 userName = tokenProcessor.getUserName(token);
				 logger.info("Token UserName ={} ",userName);
				 if(userName!=null && SecurityContextHolder.getContext().getAuthentication() == null) {
					 UserDetails userDetail = userDetailService.loadUserByUsername(userName);
					 logger.info("UserDetails : {} ",userDetail.getUsername());
					 if(tokenProcessor.validateToken(token, userDetail)) {
						 UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName, null,userDetail.getAuthorities());
						 usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
						 SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
					 }else {
						 logger.info("Token is invaled");
					 }
				 }
			 }catch(Exception e) {
				 e.printStackTrace();
			 }	 
		 }
		 chain.doFilter(request, response);
	}

}
