package com.specialized.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.specialized.model.User;
import com.specialized.repository.UserRepository;

@RestController
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @RequestMapping("/user")
    public User get(@RequestParam(value="id") long id) {
        return userRepository.findOne(id);
    }
}
