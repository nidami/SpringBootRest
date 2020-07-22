package com.deew.AppServer.authentication;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.mockito.internal.util.reflection.GenericTypeExtractor;
import org.slf4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenProcessorImpl implements TokenProcessor{
	Logger logger =org.slf4j.LoggerFactory.getLogger(TokenProcessorImpl.class);

	private final String secretKey = "SMOKEWEED";
	
	@Override
	public String generateToken(UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		return generateJWTToken(claims, userDetails.getUsername());
	}
	
	@Override
	public String getUserName(String token) throws Exception {
		return getUserNameFromToken(token);
	}
	
	@Override
	public boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUserNameFromToken(token);
		return (username.equals(userDetails.getUsername()));
		}

	private boolean isTokenExpired(String token) {
		Date date = new Date();
		logger.info("Token Expiration date ={} ",getExpirationDate(token));
		return date.before(getExpirationDate(token));
	}

	private String generateJWTToken(Map<String, Object> claims, String username) {
	    LocalDateTime now = LocalDateTime.now();
	    now=now.plusMinutes(15);
		return Jwts.builder().addClaims(claims).setSubject(username)
		.setIssuedAt(new Date()).setExpiration(convertDateTimeToDate(now))
		.signWith(SignatureAlgorithm.HS512, secretKey).compact();
	}
	
	private Date convertDateTimeToDate(LocalDateTime time) {
		return Timestamp.valueOf(time);
	}
	
	private Claims getClaims(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}
	
	private String getUserNameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}
	
	private Date getExpirationDate(String token) {
		return getClaimFromToken(token,Claims::getExpiration);
	}

	private <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
			final Claims claims = getClaims(token);
		
			return claimsResolver.apply(claims);
	}


	
	
	
	 


}
