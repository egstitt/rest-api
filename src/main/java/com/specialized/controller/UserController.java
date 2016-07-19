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
    	user = userRepository.save(user);
    	
    	// TODO: add the link to the resource in the header.
    	
    	return ResponseEntity.status(HttpStatus.CREATED).body(new StatusDTO("success"));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User get(@PathVariable("id") long id) {
    	
    	// TODO: check for existing. Guess this is why I need to throw exceptions.

//    	User user = userRepository.findOne(id);
//    	if (user == null) {
//    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusDTO("user not found"));
//    	}

    	return userRepository.findOne(id);
    }
    
    @RequestMapping(method = RequestMethod.PUT) 
    public ResponseEntity<StatusDTO> update(@RequestBody User user) {
    	
    	if (user.getId() == null) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new StatusDTO("id required"));
    	}
    	
    	user = userRepository.save(user);
    	return ResponseEntity.status(HttpStatus.OK).body(new StatusDTO("success"));
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<StatusDTO> delete(@PathVariable("id") long id) {

    	User user = userRepository.findOne(id);
    	if (user == null) {
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new StatusDTO("user not found"));
    	}
    	
    	userRepository.delete(id);
    	return ResponseEntity.status(HttpStatus.OK).body(new StatusDTO("success"));
    }
}
