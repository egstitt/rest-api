package com.specialized.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.specialized.model.SensorPoint;

public interface SensorPointRepository extends PagingAndSortingRepository<SensorPoint, Long> {

}
