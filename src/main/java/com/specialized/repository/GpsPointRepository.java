package com.specialized.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.specialized.model.GpsPoint;

public interface GpsPointRepository extends PagingAndSortingRepository<GpsPoint, Long> {

    public List<GpsPoint> findByRideId(Long rideId);
    public Page<GpsPoint> findByRideId(Pageable pageable, Long rideId);
    
}
