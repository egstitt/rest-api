package com.specialized.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.specialized.model.Account;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {

    public Account findByUsername(String username);

    public Account findByEmailAddress(String emailAddress);
}
