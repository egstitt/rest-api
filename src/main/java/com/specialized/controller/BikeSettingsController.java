package com.specialized.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.specialized.dto.StatusDTO;
import com.specialized.exception.BadRequestException;
import com.specialized.model.BikeSettings;
import com.specialized.repository.BikeSettingsRepository;

@RestController
@RequestMapping(value = "/bike_settings")
public class BikeSettingsController {
    
    @Autowired
    private BikeSettingsRepository bikeSettingsRepository;
    
    @RequestMapping(method = RequestMethod.POST) 
    public ResponseEntity<?> create(@RequestBody @Valid BikeSettings bikeSettings) {

        // TODO: validation of some kind.

        bikeSettings = bikeSettingsRepository.save(bikeSettings);
        
        // Set the location header and return the response.
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(bikeSettings.getId()).toUri());
        return new ResponseEntity<>(null, headers, HttpStatus.CREATED);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> get(@RequestParam("rider") @NotNull String rider) {
        
        // Grab the bike settings for the given rider.
        BikeSettings bikeSettings = bikeSettingsRepository.findByRider(rider);
        if (bikeSettings == null) throw new BikeSettingsNotFoundException(rider);

        return ResponseEntity.status(HttpStatus.OK).body(bikeSettings);
    }
    
    @RequestMapping(method = RequestMethod.PUT) 
    public ResponseEntity<StatusDTO> update(@RequestBody @Valid BikeSettings bikeSettings) {
        if (bikeSettings.getId() == null) throw new BadRequestException("Id required");
        
        BikeSettings existing = bikeSettingsRepository.findOne(bikeSettings.getId());
        if (existing == null) throw new BikeSettingsNotFoundException(bikeSettings.getId()); 

        // Copy over editable properties and save.
        BeanUtils.copyProperties(bikeSettings, existing, "createDate", "createUser");
        existing = bikeSettingsRepository.save(existing);
        return ResponseEntity.status(HttpStatus.OK).body(StatusDTO.success());
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<StatusDTO> delete(@PathVariable("id") @NotNull Long id) {

        // Make sure the account exists.
        BikeSettings existing = bikeSettingsRepository.findOne(id);
        if (existing == null) throw new BikeSettingsNotFoundException(id); 

        bikeSettingsRepository.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(StatusDTO.success());
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class BikeSettingsNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BikeSettingsNotFoundException(Long id) {
        super("could not find bike settings '" + id + "'");
    }
    
    public BikeSettingsNotFoundException(String rider) {
        super("could not find bike settings for rider '" + rider + "'");
    }
}
