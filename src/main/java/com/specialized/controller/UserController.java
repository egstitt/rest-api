package com.specialized.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.specialized.dto.StatusDTO;
import com.specialized.model.User;
import com.specialized.repository.UserRepository;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @RequestMapping(method = RequestMethod.GET)
    public User get(@RequestParam(value="id") long id) {
        return userRepository.findOne(id);
    }
    
    @RequestMapping(method = RequestMethod.POST) 
    public ResponseEntity<StatusDTO> create(@RequestBody @Valid User user) {
    	
    	// TODO: actually create the user.
    	user = userRepository.save(user);

    	return ResponseEntity.status(HttpStatus.CREATED).body(new StatusDTO("success"));
    }
}
