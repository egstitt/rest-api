package com.specialized.repository;

import org.springframework.data.repository.CrudRepository;

import com.specialized.model.BikeSettings;

public interface BikeSettingsRepository extends CrudRepository<BikeSettings, Long> {

    public BikeSettings findByRider(String rider);
    
}
