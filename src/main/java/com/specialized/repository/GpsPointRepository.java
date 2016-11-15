package com.specialized.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.specialized.model.GpsPoint;

public interface GpsPointRepository extends PagingAndSortingRepository<GpsPoint, Long> {

}
