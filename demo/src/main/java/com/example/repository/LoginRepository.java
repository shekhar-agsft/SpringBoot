package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.security.core.userdetails.User;

import com.example.model.LoginDetails;

public interface LoginRepository extends MongoRepository<LoginDetails, String> {

	public User findByUserName(String firstName);

}
