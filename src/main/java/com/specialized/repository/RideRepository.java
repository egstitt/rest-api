package com.specialized.repository;

import org.springframework.data.repository.CrudRepository;

import com.specialized.model.Ride;

public interface RideRepository extends CrudRepository<Ride, Long> {

}