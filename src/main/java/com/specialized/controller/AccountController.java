package com.specialized.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.specialized.dto.StatusDTO;
import com.specialized.exception.BadRequestException;
import com.specialized.model.Account;
import com.specialized.repository.AccountRepository;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController {

    @Autowired
    private AccountRepository accountRepository;

    /**
     * Create account.
     * 
     * @param account
     * @return
     */
    @RequestMapping(method = RequestMethod.POST) 
    public ResponseEntity<?> create(@RequestBody @Valid Account account) {

        // Validation.
        if (StringUtils.length(account.getPassword()) < 8) throw new BadRequestException("Password must be 8 characters");
        
        // Check for existing.
        Account existing = accountRepository.findByUsername(account.getUsername());
        if (existing != null) throw new AccountAlreadyExistsException(account.getUsername());

        existing = accountRepository.findByEmailAddress(account.getEmailAddress());
        if (existing != null) throw new AccountAlreadyExistsException(account.getEmailAddress());

        // Encrypt the password and save the account.
        StandardPasswordEncoder encoder = new StandardPasswordEncoder("secret");
        account.setPassword(encoder.encode(account.getPassword()));
        account = accountRepository.save(account);
        
        // Set the location header and return the response.
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(account.getId()).toUri());
        return new ResponseEntity<>(null, headers, HttpStatus.CREATED);
    }

    /**
     * Get account by Id.
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable("id") @NotNull Long id) {

        // Make sure the account exists.
        Account account = accountRepository.findOne(id);
        if (account == null) throw new AccountNotFoundException(id); 

        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    /**
     * Get paginated and sortable account list.
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getList(Pageable pageable) {
        Page<Account> page = accountRepository.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    /**
     * Update account.
     * 
     * @param account
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT) 
    public ResponseEntity<StatusDTO> update(@RequestBody @Valid Account account) {
        if (account.getId() == null) throw new BadRequestException("Id required");
        
        Account existing = accountRepository.findOne(account.getId());
        if (existing == null) throw new AccountNotFoundException(account.getId()); 

        // Copy over editable properties and save.
        BeanUtils.copyProperties(account, existing, "password", "createDate", "createAccount");
        existing = accountRepository.save(existing);
        return ResponseEntity.status(HttpStatus.OK).body(StatusDTO.success());
    }

    /**
     * Delete account by Id.
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<StatusDTO> delete(@PathVariable("id") @NotNull Long id) {

        // Make sure the account exists.
        Account existing = accountRepository.findOne(id);
        if (existing == null) throw new AccountNotFoundException(id); 

        accountRepository.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(StatusDTO.success());
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class AccountNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AccountNotFoundException(Long accountId) {
        super("could not find account '" + accountId + "'.");
    }
}

@ResponseStatus(HttpStatus.CONFLICT)
class AccountAlreadyExistsException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AccountAlreadyExistsException(String account) {
        super("account '" + account + "' already exists.");
    }
}
