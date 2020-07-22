package com.deew.AppServer.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.deew.AppServer.modal.User;


public interface UserDetailRepository extends JpaRepository<User, Integer>  {
   public Optional<User> findByUserName(String userName);
}
