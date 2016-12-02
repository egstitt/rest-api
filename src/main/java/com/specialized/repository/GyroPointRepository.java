package com.specialized.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.specialized.model.GyroPoint;

public interface GyroPointRepository extends PagingAndSortingRepository<GyroPoint, Long> {

    public List<GyroPoint> findByRideId(Long rideId);
    public Page<GyroPoint> findByRideId(Pageable pageable, Long rideId);

}
