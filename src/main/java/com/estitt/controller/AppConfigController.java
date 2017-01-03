package com.estitt.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estitt.model.AppConfig;
import com.estitt.repository.AppConfigRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/configs")
public class AppConfigController extends BaseController {

    @Autowired
    private AppConfigRepository appConfigRepository;

    @ApiOperation(value = "Create an app config", notes = "Creates the given app config")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody AppConfig appConfig) {
        appConfig = appConfigRepository.save(appConfig);
        return buildCreateResponse(appConfig);
    }     

    @ApiOperation(value = "Get app config", notes = "Get the most recently updated app config", response = AppConfig.class)
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> get() {

        // Grab the newest one and return it.
        AppConfig appConfig = appConfigRepository.findFirstByOrderByUpdateDateDesc();
        if (appConfig == null) throw new EntityNotFoundException("could not find app config.");

        return ResponseEntity.status(HttpStatus.OK).body(appConfig);
    }
}
