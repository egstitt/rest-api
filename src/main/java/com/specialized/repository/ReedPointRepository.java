package com.specialized.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.specialized.model.ReedPoint;

public interface ReedPointRepository extends PagingAndSortingRepository<ReedPoint, Long> {

    public Page<ReedPoint> findByRideId(Pageable pageable, Long rideId);

}
