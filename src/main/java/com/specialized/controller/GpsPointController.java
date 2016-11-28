package com.specialized.controller;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.specialized.model.GpsPoint;
import com.specialized.repository.GpsPointRepository;

@RestController
@RequestMapping(value = "/gps_points")
public class GpsPointController {

    @Autowired
    private GpsPointRepository gpsPointRepository;
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getList(Pageable pageable, @RequestParam("rideId") @NotNull Long rideId) {
        Page<GpsPoint> page =  gpsPointRepository.findByRideId(pageable, rideId);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }
}
