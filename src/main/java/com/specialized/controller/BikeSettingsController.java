package com.specialized.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.specialized.dto.StatusDTO;
import com.specialized.exception.BadRequestException;
import com.specialized.model.Account;
import com.specialized.model.BikeSettings;
import com.specialized.repository.AccountRepository;
import com.specialized.repository.BikeSettingsRepository;

@RestController
@RequestMapping(value = "/bike_settings")
public class BikeSettingsController extends SBCController {
    
    @Autowired
    private AccountRepository accountRepository;
    
    @Autowired
    private BikeSettingsRepository bikeSettingsRepository;
    
    @RequestMapping(method = RequestMethod.POST) 
    public ResponseEntity<?> create(@RequestBody @Valid BikeSettings bikeSettings) {

        // Check for valid account.
        Account account = accountRepository.findByUsername(bikeSettings.getRider());
        if (account == null) throw new BadRequestException("Rider '" + bikeSettings.getRider() + "' does not have a valid account.");

        // Check for existing.
        BikeSettings existing = bikeSettingsRepository.findByRider(bikeSettings.getRider());
        if (existing != null) throw new EntityAlreadyExistsException("bike settings already exist for rider '" + bikeSettings.getRider() + "'.");
        
        bikeSettings = bikeSettingsRepository.save(bikeSettings);
        return buildCreateResponse(bikeSettings);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> get(@RequestParam("rider") @NotNull String rider) {
        
        // Grab the bike settings for the given rider.
        BikeSettings bikeSettings = bikeSettingsRepository.findByRider(rider);
        if (bikeSettings == null) throw new EntityNotFoundException("could not find bike settings for rider '" + rider + "'");

        return ResponseEntity.status(HttpStatus.OK).body(bikeSettings);
    }
    
    @RequestMapping(method = RequestMethod.PUT) 
    public ResponseEntity<StatusDTO> update(@RequestBody @Valid BikeSettings bikeSettings) {
        if (bikeSettings.getId() == null) throw new BadRequestException("Id required");
        
        BikeSettings existing = bikeSettingsRepository.findOne(bikeSettings.getId());
        if (existing == null) throw new EntityNotFoundException("could not find bike settings '" + bikeSettings.getId() + "'.");  

        // Copy over editable properties and save.
        BeanUtils.copyProperties(bikeSettings, existing, "rider", "createDate", "createAccount");
        existing = bikeSettingsRepository.save(existing);
        return ResponseEntity.status(HttpStatus.OK).body(StatusDTO.success());
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<StatusDTO> delete(@PathVariable("id") @NotNull Long id) {

        // Make sure the account exists.
        BikeSettings existing = bikeSettingsRepository.findOne(id);
        if (existing == null) throw new EntityNotFoundException("could not find bike settings '" + id + "'."); 

        bikeSettingsRepository.delete(id);
        return ResponseEntity.status(HttpStatus.OK).body(StatusDTO.success());
    }
}
