package com.specialized.repository;

import org.springframework.data.repository.CrudRepository;

import com.specialized.model.SensorPoint;

public interface SensorPointRepository extends CrudRepository<SensorPoint, Long> {

}
