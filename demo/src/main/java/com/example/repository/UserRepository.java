package com.example.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.model.User;

public interface UserRepository extends MongoRepository<User, Long> {
	@Query("{ 'username' : ?0 }")
	User findByUsername(String username);

	@Query("{ 'email' : ?0 }")
	User findByEmail(String email);
}
