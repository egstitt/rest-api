package com.specialized.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.specialized.model.Ride;

public interface RideRepository extends CrudRepository<Ride, Long> {

    public List<Ride> findByMacAddress(String macAddress);
}