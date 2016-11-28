package com.specialized.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.specialized.model.AccPoint;
import com.specialized.repository.AccPointRepository;

@RestController
@RequestMapping(value = "/acc_points")
public class AccPointController {

    @Autowired
    private AccPointRepository accPointRepository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getList(Pageable pageable) {
        Page<AccPoint> page = accPointRepository.findAll(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(page);
    }
}
