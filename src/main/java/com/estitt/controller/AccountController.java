package com.estitt.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estitt.model.Account;
import com.estitt.model.dto.StatusDTO;
import com.estitt.repository.AccountRepository;

@RestController
@RequestMapping(value = "/accounts")
public class AccountController extends BaseController {
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
        if (existing != null) throw new EntityAlreadyExistsException("account '" + account.getUsername() + "' already exists.");

        existing = accountRepository.findByEmailAddress(account.getEmailAddress());
        if (existing != null) throw new EntityAlreadyExistsException("account '" + account.getEmailAddress() + "' already exists.");

        // Encrypt the password and save the account.
        StandardPasswordEncoder encoder = new StandardPasswordEncoder("secret");
        account.setPassword(encoder.encode(account.getPassword()));
        account = accountRepository.save(account);
        return buildCreateResponse(account);
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
        if (account == null) throw new EntityNotFoundException("could not find account '" + id + "'."); 

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
        if (existing == null) throw new EntityNotFoundException("could not find account '" + account.getId() + "'."); 

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
        if (existing == null) throw new EntityNotFoundException("could not find account '" + id + "'."); 

        accountRepository.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(StatusDTO.success());
    }
}
