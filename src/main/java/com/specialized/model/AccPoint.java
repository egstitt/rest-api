package com.specialized.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="acc_point")
public class AccPoint extends ModelBase {

    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "acc_points_seq_gen")
    @SequenceGenerator(name = "acc_points_seq_gen", sequenceName = "sbc_acc_point_id_seq", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name="ride_id")
    private Long rideId;
    
    @Column(name="time_stamp")
    private Long timeStamp;
    
    @Column(name="accx")
    private float accx;
    
    @Column(name="accy")
    private float accy;

    @Column(name="accz")
    private float accz;

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

    public float getAccx() {
        return accx;
    }

    public void setAccx(float accx) {
        this.accx = accx;
    }

    public float getAccy() {
        return accy;
    }

    public void setAccy(float accy) {
        this.accy = accy;
    }

    public float getAccz() {
        return accz;
    }

    public void setAccz(float accz) {
        this.accz = accz;
    }

    public Long getRideId() {
        return rideId;
    }

    public void setRideId(Long rideId) {
        this.rideId = rideId;
    }
}
