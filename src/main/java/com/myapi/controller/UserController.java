package com.myapi.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.myapi.dto.StatusDTO;
import com.myapi.model.User;
import com.myapi.repository.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * Create user.
     * 
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.POST) 
    public ResponseEntity<?> create(@RequestBody @Valid User user) {

        // Check for existing.
        User existing = userRepository.findByUsername(user.getUsername());
        if (existing == null) {
            existing = userRepository.findByEmailAddress(user.getEmailAddress());
        }
        if (existing != null) {
            // TODO: should throw an exception?
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new StatusDTO("user already exists"));
        }

        user = userRepository.save(user);
        
        // Set the location header and return the response.
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(user.getId()).toUri());
        return new ResponseEntity<>(null, headers, HttpStatus.CREATED);
    }

    /**
     * Get user by Id.
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable("id") long id) {

        // Make sure the user exists.
        User user = userRepository.findOne(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusDTO("user not found"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    /**
     * Get paginated and sortable user list.
     * 
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getList(Pageable pageable) {
        Page<User> page = userRepository.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    /**
     * Update user.
     * 
     * @param user
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT) 
    public ResponseEntity<StatusDTO> update(@RequestBody @Valid User user) {

        if (user.getId() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StatusDTO("id required"));
        }

        User existing = userRepository.findOne(user.getId());
        if (existing == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusDTO("user not found"));
        }

        user = userRepository.save(user);
        return ResponseEntity.status(HttpStatus.OK).body(StatusDTO.success());
    }

    /**
     * Delete user by Id.
     * 
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<StatusDTO> delete(@PathVariable("id") long id) {

        // Make sure the user exists.
        User user = userRepository.findOne(id);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusDTO("user not found"));
        }

        userRepository.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(StatusDTO.success());
    }
}
