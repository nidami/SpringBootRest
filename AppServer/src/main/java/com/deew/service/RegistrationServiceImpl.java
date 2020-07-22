package com.deew.service;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deew.AppServer.Exception.UserAlreadyExistException;
import com.deew.AppServer.modal.RegisterRequest;
import com.deew.AppServer.modal.User;
import com.deew.AppServer.repository.UserDetailRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService{

	private static Logger logger = LoggerFactory.getLogger(RegistrationServiceImpl.class);
	
	@Autowired
	private UserDetailRepository userRepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public void registerUser(RegisterRequest register) {
	   Optional<User> existingUser=userRepo.findByUserName(register.getUserName());
	   if(!existingUser.isPresent()) {
		  User user = mapper.map(register, User.class);
		  userRepo.save(user);
		  logger.info("User Register succesfully username ={} ",register.getUserName() );
		  
	   }else {
		   throw new UserAlreadyExistException("User Name Already Taken");
	   }
		//return userRepo.save(register);
	}

}
