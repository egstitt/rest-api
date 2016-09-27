package com.myapi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.myapi.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    public User findByUsername(String username);

    public User findByEmailAddress(String emailAddress);
}
