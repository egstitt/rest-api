package com.specialized.repository;

import org.springframework.data.repository.CrudRepository;

import com.specialized.model.GpsPoint;

public interface GpsPointRepository extends CrudRepository<GpsPoint, Long> {

}
