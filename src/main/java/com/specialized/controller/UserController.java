package com.specialized.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.specialized.dto.StatusDTO;
import com.specialized.model.User;
import com.specialized.repository.UserRepository;

@RestController
@RequestMapping(value = "/users")
public class UserController {

	@Autowired
    private UserRepository userRepository;
    
    @RequestMapping(method = RequestMethod.POST) 
    public ResponseEntity<StatusDTO> create(@RequestBody @Valid User user) {
    	
    	// TODO: handle errors.
    	user = userRepository.save(user);
    	return ResponseEntity.status(HttpStatus.CREATED).body(new StatusDTO("success"));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") long id) {
        return userRepository.findOne(id);
        
        // TODO: handle errors (not found)
    }
    
    @RequestMapping(method = RequestMethod.PUT) 
    public ResponseEntity<StatusDTO> update(@RequestBody @Valid User user) {
    	
    	// TODO: handle errors.
    	user = userRepository.save(user);
    	return ResponseEntity.status(HttpStatus.OK).body(new StatusDTO("success"));
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<StatusDTO> delete(@PathVariable("id") long id) {

    	// TODO: handle errors (doesn't exist)
    	
    	userRepository.delete(id);
    	return ResponseEntity.status(HttpStatus.OK).body(new StatusDTO("success"));
    }
}
