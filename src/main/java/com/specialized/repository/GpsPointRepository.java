package com.specialized.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.specialized.model.GpsPoint;

public interface GpsPointRepository extends PagingAndSortingRepository<GpsPoint, Long> {
    
    public Page<GpsPoint> findByRideId(Pageable pageable, Long rideId);
    
}
