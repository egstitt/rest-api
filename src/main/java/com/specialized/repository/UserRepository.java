package com.specialized.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.specialized.model.User;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    public User findByUsername(String username);

    public User findByEmailAddress(String emailAddress);
}
