package com.specialized.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.specialized.model.ReedPoint;

public interface ReedPointRepository extends PagingAndSortingRepository<ReedPoint, Long> {

    public List<ReedPoint> findByRideId(Long rideId);
    public Page<ReedPoint> findByRideId(Pageable pageable, Long rideId);

}
