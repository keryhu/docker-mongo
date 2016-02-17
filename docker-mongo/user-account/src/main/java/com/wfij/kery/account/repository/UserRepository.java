package com.wfij.kery.account.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;

import com.wfij.kery.account.domain.User;


public interface UserRepository extends MongoRepository<User,String>{
	
	 public User findByEmail(@Param("email") String email);
	   public User findByPhone(@Param("phone")String phone);
	   public User findById(@Param("id") String id);
	  
	   public User deleteByEmail (String email);
	   public User deleteByPhone (String phone);
}
