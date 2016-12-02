package com.specialized.controller;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.specialized.model.AccPoint;
import com.specialized.model.GpsPoint;
import com.specialized.model.GyroPoint;
import com.specialized.model.ReedPoint;
import com.specialized.model.Ride;
import com.specialized.repository.AccPointRepository;
import com.specialized.repository.GpsPointRepository;
import com.specialized.repository.GyroPointRepository;
import com.specialized.repository.ReedPointRepository;
import com.specialized.repository.RideRepository;

@RestController
@RequestMapping(value = "/rides")
public class RideController extends SBCController {

    @Autowired
    private RideRepository rideRepository;

    @Autowired 
    private GpsPointRepository gpsPointRepository;
    
    @Autowired
    private AccPointRepository accPointRepository;
    
    @Autowired
    private GyroPointRepository gyroPointRepository;
    
    @Autowired
    private ReedPointRepository reedPointRepository;
    
    @RequestMapping(method = RequestMethod.POST) 
    public ResponseEntity<?> create(@RequestBody @Valid Ride ride) {

        // TODO: validation.

        ride = rideRepository.save(ride);
        
        // Handle GPS points.
        if (ride.getGpsData().size() > 0) {
            for (GpsPoint gpsPoint : ride.getGpsData()) {
                gpsPoint.setRideId(ride.getId());
            }
            gpsPointRepository.save(ride.getGpsData());
        }
        
        // Handle acc points.
        if (ride.getAccData().size() > 0) {
            for (AccPoint accPoint : ride.getAccData()) {
                accPoint.setRideId(ride.getId());
            }
            accPointRepository.save(ride.getAccData());
        }
        
        // Handle gyro points.
        if (ride.getGyroData().size() > 0) {
            for (GyroPoint gyroPoint : ride.getGyroData()) {
                gyroPoint.setRideId(ride.getId());
            }
            gyroPointRepository.save(ride.getGyroData());
        }
        
        // Handle reed points.
        if (ride.getReedData().size() > 0) {
            for (ReedPoint reedPoint : ride.getReedData()) {
                reedPoint.setRideId(ride.getId());
            }
            reedPointRepository.save(ride.getReedData());
        }
        
        return buildCreateResponse(ride);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<?> get(@PathVariable("id") @NotNull Long id) {

        Ride ride = rideRepository.findOne(id);
        if (ride == null) throw new EntityNotFoundException("could not find ride '" + id + "'.");
        
        // Populate child data.
        List<GpsPoint> gpsData = gpsPointRepository.findByRideId(id);
        ride.setGpsData(gpsData);
        
        List<AccPoint> accData = accPointRepository.findByRideId(id);
        ride.setAccData(accData);
        
        List<GyroPoint> gyroData = gyroPointRepository.findByRideId(id);
        ride.setGyroData(gyroData);
        
        List<ReedPoint> reedData = reedPointRepository.findByRideId(id);
        ride.setReedData(reedData);
        
        return ResponseEntity.status(HttpStatus.OK).body(ride);
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getListByMacAddress(@RequestParam("macAddress") @NotNull String macAddress) {

        List<Ride> rides = rideRepository.findByMacAddress(macAddress);
        return ResponseEntity.status(HttpStatus.OK).body(rides);
    }
}
