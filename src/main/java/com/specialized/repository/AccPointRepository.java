package com.specialized.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.specialized.model.AccPoint;

public interface AccPointRepository extends PagingAndSortingRepository<AccPoint, Long> {

}
