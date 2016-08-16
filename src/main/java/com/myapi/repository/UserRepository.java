package com.myapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.myapi.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	public User findByUsername(String username);
	
	public User findByEmailAddress(String emailAddress);
}
