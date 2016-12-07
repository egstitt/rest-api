package com.estitt.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.estitt.model.Account;
import com.estitt.repository.AccountRepository;

/**
 * This is used for user audit columns.
 *
 */
public class SpringSecurityAuditorAware implements AuditorAware<Long> {

    @Autowired
    private AccountRepository accountRepository;
    
    @Override
    public Long getCurrentAuditor() {
        
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
 
        String username = ((User) authentication.getPrincipal()).getUsername();
        Account account = accountRepository.findByUsername(username);
        return account.getId();
    }
}
