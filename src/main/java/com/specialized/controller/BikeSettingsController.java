package com.specialized.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.specialized.model.BikeSettings;
import com.specialized.repository.BikeSettingsRepository;

@RestController
@RequestMapping(value = "/bike_settings")
public class BikeSettingsController extends SBCController {
    
    @Autowired
    private BikeSettingsRepository bikeSettingsRepository;
    
    @RequestMapping(method = RequestMethod.POST) 
    public ResponseEntity<?> create(@RequestBody @Valid BikeSettings bikeSettings) {
        
        // TODO: handle the case of a passed-in id. This should NOT update an existing one. Just null it out?
        
        bikeSettings = bikeSettingsRepository.save(bikeSettings);
        return buildCreateResponse(bikeSettings);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> get() {
        List<BikeSettings> bikeSettingsList = bikeSettingsRepository.findAllByOrderByUpdateDateDesc();
        if (bikeSettingsList == null || bikeSettingsList.size() == 0) {
            throw new EntityNotFoundException("Bike settings not found.");
        }
        
        return ResponseEntity.status(HttpStatus.OK).body(bikeSettingsList.get(0));
    }    
}
