package com.specialized.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.specialized.model.BikeSettings;
import com.specialized.repository.BikeSettingsRepository;

@RestController
@RequestMapping(value = "/bike_settings")
public class BikeSettingsController {
    
    @Autowired
    private BikeSettingsRepository bikeSettingsRepository;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> get(@RequestParam("rider") @NotNull String rider) {
        
        // Grab the bike settings for the given rider.
        BikeSettings bikeSettings = bikeSettingsRepository.findByRider(rider);
        if (bikeSettings == null) throw new BikeSettingsNotFoundException(rider);

        return ResponseEntity.status(HttpStatus.OK).body(bikeSettings);
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class BikeSettingsNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BikeSettingsNotFoundException(String rider) {
        super("could not find bike settings for rider '" + rider + "'");
    }
}
