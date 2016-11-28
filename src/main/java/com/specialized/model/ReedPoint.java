package com.specialized.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="reed_point")
public class ReedPoint extends ModelBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reed_points_seq_gen")
    @SequenceGenerator(name = "reed_points_seq_gen", sequenceName = "sbc_reed_point_id_seq", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name="ride_id")
    private Long rideId;

    @Column(name="time_stamp")
    private Long timeStamp;
    
    @Column(name="adc")
    private Integer adc;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Integer getAdc() {
        return adc;
    }

    public void setAdc(Integer adc) {
        this.adc = adc;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }
}
