package com.specialized.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="gyro_point")
public class GyroPoint extends ModelBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gyro_points_seq_gen")
    @SequenceGenerator(name = "gyro_points_seq_gen", sequenceName = "sbc_gyro_point_id_seq", allocationSize = 1, initialValue = 1)
    private Long id;
    
    @Column(name="ride_id")
    private Long rideId;

    @Column(name="time_stamp")
    private Long timeStamp;
    
    @Column(name="gyrx")
    private float gyrx;

    @Column(name="gyry")
    private float gyry;

    @Column(name="gyrz")
    private float gyrz;

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

    public float getGyrx() {
        return gyrx;
    }

    public void setGyrx(float gyrx) {
        this.gyrx = gyrx;
    }

    public float getGyry() {
        return gyry;
    }

    public void setGyry(float gyry) {
        this.gyry = gyry;
    }

    public float getGyrz() {
        return gyrz;
    }

    public void setGyrz(float gyrz) {
        this.gyrz = gyrz;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }
}
