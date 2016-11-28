package com.specialized.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.specialized.model.GpsPoint;
import com.specialized.model.Ride;
import com.specialized.model.SensorPoint;
import com.specialized.repository.GpsPointRepository;
import com.specialized.repository.RideRepository;
import com.specialized.repository.SensorPointRepository;

@RestController
@RequestMapping(value = "/rides")
public class RideController extends SBCController {

    @Autowired
    private RideRepository rideRepository;

    @Autowired 
    GpsPointRepository gpsPointRepository;
    
    @Autowired
    SensorPointRepository sensorPointRepository;
    
    @RequestMapping(method = RequestMethod.POST) 
    public ResponseEntity<?> create(@RequestBody @Valid Ride ride) {

        // TODO: validation.

        ride = rideRepository.save(ride);
        
        // Handle GPS points.
        if (ride.getGpsPoints().size() > 0) {
            for (GpsPoint gpsPoint : ride.getGpsPoints()) {
                gpsPoint.setRideId(ride.getId());
            }
            gpsPointRepository.save(ride.getGpsPoints());
        }
        
        // Handle sensor points.
        if (ride.getSensorPoints().size() > 0) {
            for (SensorPoint sensorPoint : ride.getSensorPoints()) {
                sensorPoint.setRideId(ride.getId());
            }
            sensorPointRepository.save(ride.getSensorPoints());
        }
        
        // TODO: handle the remaining ride child entities.
        
        return buildCreateResponse(ride);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable("id") @NotNull Long id) {

        Ride ride = rideRepository.findOne(id);
        if (ride == null) throw new EntityNotFoundException("could not find ride '" + id + "'.");
        
        return ResponseEntity.status(HttpStatus.OK).body(ride);
    }
}
