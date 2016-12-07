package com.estitt.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.estitt.model.AppConfig;

public interface AppConfigRepository extends CrudRepository<AppConfig, Long> {

    public List<AppConfig> findAllByOrderByCreateDateDesc();
}
