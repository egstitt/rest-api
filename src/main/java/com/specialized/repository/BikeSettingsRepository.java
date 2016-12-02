package com.specialized.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.specialized.model.BikeSettings;

public interface BikeSettingsRepository extends CrudRepository<BikeSettings, Long> {

    public List<BikeSettings> findAllByOrderByUpdateDateDesc();
    
}
