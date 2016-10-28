package com.specialized;

import org.springframework.data.domain.AuditorAware;

/**
 * This is used for user audit columns.
 *
 */
public class SpringSecurityAuditorAware implements AuditorAware<Long> {

    @Override
    public Long getCurrentAuditor() {
        
        // TODO: pull the id of the authenticated user.]
        
        return 1L;
    }
}
