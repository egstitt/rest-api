package com.specialized.config.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.specialized.repository.AccountRepository;

/**
 * Configuration of basic auth to use our Account repository.
 *
 */
//@Configuration
public class GlobalAuthConfig extends GlobalAuthenticationConfigurerAdapter {

//    @Value("${io.url}")
//    private String ioUrl;
//   
//    @Value("${io.username}")
//    private String ioUsername;
//    
//    @Value("${io.password}")
//    private String ioPassword;
    
    @Autowired
    AccountRepository accountRepository;

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService());
    }

    @Bean
    UserDetailsService userDetailsService() {
        return new UserDetailsService() {

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                
                // TODO: hit io.s.com and ask if the username is correct (?)
                return null;
                
//                Account account = accountRepository.findByUsername(username);
//                if (account == null) throw new UsernameNotFoundException("could not find the user '" + username + "'");
//                return new User(
//                        account.getUsername(), 
//                        account.getPassword(), true, true, true, true,
//                        AuthorityUtils.createAuthorityList("USER"));
            }
        };
    }
}
