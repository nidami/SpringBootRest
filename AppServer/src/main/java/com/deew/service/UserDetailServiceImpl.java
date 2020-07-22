package com.deew.service;

import java.util.ArrayList;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.deew.AppServer.modal.User;
import com.deew.AppServer.repository.UserDetailRepository;

@Component
public class UserDetailServiceImpl implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);
	@Autowired
	private UserDetailRepository userDetailRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> user = userDetailRepository.findByUserName(username);
	
		 if(user.isPresent()) {
		  org.springframework.security.core.userdetails.User newuser=new org.springframework.security.core.userdetails.User(user.get().getUserName(), user.get().getPassword(), new ArrayList<>());
		  logger.info("DB user = {} {} {}",user.get().getUserName(),user.get(). getPassword() ); 
		  return newuser;
		 }else {
			 logger.info("No user found in the system name={}AND",username);
			 throw new UsernameNotFoundException("User Not Found in the system");
		 }
		 //org.springframework.security.core.userdetails.User myUser = new org.springframework.security.core.userdetails.User("xyz", "xyz", new ArrayList<>());
		 
	}

}
