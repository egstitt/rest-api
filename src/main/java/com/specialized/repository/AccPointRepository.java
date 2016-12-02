package com.specialized.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.specialized.model.AccPoint;

public interface AccPointRepository extends PagingAndSortingRepository<AccPoint, Long> {

    public List<AccPoint> findByRideId(Long rideId);
    public Page<AccPoint> findByRideId(Pageable pageable, Long rideId);
    
}
