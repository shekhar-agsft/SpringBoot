package com.example.utility;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.security.Role;



public interface AuthorityRepository extends MongoRepository<Role, Long> {
	
	@Query("{ 'name' : ?0 }")
	Role findByName(String name);

}
