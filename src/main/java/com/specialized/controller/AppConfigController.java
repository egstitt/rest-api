package com.specialized.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.specialized.APIConstants;
import com.specialized.model.AppConfig;
import com.specialized.repository.AppConfigRepository;

@RestController
@RequestMapping(value = APIConstants.APP_CONFIG_SERVICE_PATH)
public class AppConfigController {

    @Autowired
    private AppConfigRepository appConfigRepository;
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody AppConfig appConfig) {
        
        // Nuke and pave.
        appConfigRepository.deleteAll();
        appConfig = appConfigRepository.save(appConfig);

        // Set the location header and return the response.
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(appConfig.getId()).toUri());
        return new ResponseEntity<>(null, headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> get() {
        
        // Grab the latest. There can only be one. Should probably order by create time though to be safe.
        List<AppConfig> appConfigs = (List<AppConfig>) appConfigRepository.findAll();
        if (appConfigs == null || appConfigs.size() == 0) throw new AppConfigNotFoundException();
        
        return ResponseEntity.status(HttpStatus.OK).body(appConfigs.get(0));
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class AppConfigNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public AppConfigNotFoundException() {
        super("could not find app config");
    }
}
