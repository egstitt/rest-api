package com.specialized.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.specialized.model.Account;

public interface AccountRepository extends PagingAndSortingRepository<Account, Long> {

    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public Account findByUsername(String username);

    public Account findByEmailAddress(String emailAddress);
}
