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
import com.specialized.model.GpsPoint;
import com.specialized.repository.GpsPointRepository;

@RestController
@RequestMapping(value = "/gps_points")
public class GpsPointController {

    @Autowired
    private GpsPointRepository gpsPointRepository;
    
    @RequestMapping(method = RequestMethod.POST) 
    public ResponseEntity<?> create(@RequestBody @Valid GpsPoint gpsPoint) {

        // TODO: validation of some kind.
        
        gpsPoint = gpsPointRepository.save(gpsPoint);
        
        // Set the location header and return the response.
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(gpsPoint.getId()).toUri());
        return new ResponseEntity<>(null, headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/bulk", method = RequestMethod.POST) 
    public ResponseEntity<?> createList(@RequestBody @Valid List<GpsPoint> gpsPoints) {

        // TODO: validation of some kind.
        
        gpsPointRepository.save(gpsPoints);
        return ResponseEntity.status(HttpStatus.CREATED).body(StatusDTO.success());
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable("id") @NotNull Long id) {
        GpsPoint gpsPoint = gpsPointRepository.findOne(id);
        if (gpsPoint == null) throw new GpsPointNotFoundException(id); 

        return ResponseEntity.status(HttpStatus.OK).body(gpsPoint);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getList() {
        List<GpsPoint> gpsPoints = (List<GpsPoint>) gpsPointRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(gpsPoints);
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class GpsPointNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public GpsPointNotFoundException(Long id) {
        super("could not find gps point '" + id + "'.");
    }
}