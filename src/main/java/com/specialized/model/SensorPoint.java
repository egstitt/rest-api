package com.specialized.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="sensor_point")
public class SensorPoint extends ModelBase {

    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sensor_points_seq_gen")
    @SequenceGenerator(name = "sensor_points_seq_gen", sequenceName = "sbc_sensor_points_id_seq", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name="time_stamp")
    private Long time;
    
    @Column(name="acc_x")
    private float accX;
    
    @Column(name="acc_y")
    private float accY;
    
    @Column(name="acc_z")
    private float accZ;
    
    @Column(name="gyr_x")
    private float gyrX;
    
    @Column(name="gyr_y")
    private float gyrY;
    
    @Column(name="gyr_z")
    private float gyrZ;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public float getAccX() {
        return accX;
    }

    public void setAccX(float accX) {
        this.accX = accX;
    }

    public float getAccY() {
        return accY;
    }

    public void setAccY(float accY) {
        this.accY = accY;
    }

    public float getAccZ() {
        return accZ;
    }

    public void setAccZ(float accZ) {
        this.accZ = accZ;
    }

    public float getGyrX() {
        return gyrX;
    }

    public void setGyrX(float gyrX) {
        this.gyrX = gyrX;
    }

    public float getGyrY() {
        return gyrY;
    }

    public void setGyrY(float gyrY) {
        this.gyrY = gyrY;
    }

    public float getGyrZ() {
        return gyrZ;
    }

    public void setGyrZ(float gyrZ) {
        this.gyrZ = gyrZ;
    }
}
