package com.estitt.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.estitt.model.AppConfig;
import com.estitt.repository.AppConfigRepository;

@RestController
@RequestMapping(value = "/configs")
public class AppConfigController extends BaseController {

    @Autowired
    private AppConfigRepository appConfigRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody AppConfig appConfig) {
        appConfig = appConfigRepository.save(appConfig);
        return buildCreateResponse(appConfig);
    }     

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> get() {

        // Grab the newest one and return it.
        List<AppConfig> appConfigs = (List<AppConfig>) appConfigRepository.findAllByOrderByCreateDateDesc();
        if (appConfigs == null || appConfigs.size() == 0) throw new EntityNotFoundException("could not find app config.");

        return ResponseEntity.status(HttpStatus.OK).body(appConfigs.get(0));
    }
}
