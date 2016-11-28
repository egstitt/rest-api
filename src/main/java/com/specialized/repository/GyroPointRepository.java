package com.specialized.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.specialized.model.GyroPoint;

public interface GyroPointRepository extends PagingAndSortingRepository<GyroPoint, Long> {

    public Page<GyroPoint> findByRideId(Pageable pageable, Long rideId);

}
