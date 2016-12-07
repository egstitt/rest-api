package com.estitt.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.estitt.model.Account;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {

    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public Account findByUsername(String username);

    public Account findByEmailAddress(String emailAddress);
}
