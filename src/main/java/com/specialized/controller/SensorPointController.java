package com.specialized.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.specialized.dto.StatusDTO;
import com.specialized.model.SensorPoint;
import com.specialized.repository.SensorPointRepository;

@RestController
@RequestMapping(value = "/sensor_points")
public class SensorPointController {

    @Autowired
    private SensorPointRepository sensorPointRepository;

    @RequestMapping(method = RequestMethod.POST) 
    public ResponseEntity<?> create(@RequestBody @Valid SensorPoint sensorPoint) {

        // TODO: validation of some kind.
        
        sensorPoint = sensorPointRepository.save(sensorPoint);
        
        // Set the location header and return the response.
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(sensorPoint.getId()).toUri());
        return new ResponseEntity<>(null, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/bulk", method = RequestMethod.POST) 
    public ResponseEntity<?> createList(@RequestBody @Valid List<SensorPoint> sensorPoints) {

        // TODO: validation of some kind.
        
        sensorPointRepository.save(sensorPoints);
        return ResponseEntity.status(HttpStatus.CREATED).body(StatusDTO.success());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable("id") @NotNull Long id) {
        SensorPoint sensorPoint = sensorPointRepository.findOne(id);
        if (sensorPoint == null) throw new SensorPointNotFoundException(id); 

        return ResponseEntity.status(HttpStatus.OK).body(sensorPoint);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getList() {
        List<SensorPoint> sensorPoints = (List<SensorPoint>) sensorPointRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(sensorPoints);
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class SensorPointNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public SensorPointNotFoundException(Long id) {
        super("could not find sensor point '" + id + "'.");
    }
}
