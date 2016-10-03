package com.specialized.repository;

import org.springframework.data.repository.CrudRepository;

import com.specialized.model.AppConfig;

public interface AppConfigRepository extends CrudRepository<AppConfig, Long> {

}
