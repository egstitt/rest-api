package com.myapi.repository;

import org.springframework.data.repository.CrudRepository;

import com.myapi.model.AppConfig;

public interface AppConfigRepository extends CrudRepository<AppConfig, Long> {

}
