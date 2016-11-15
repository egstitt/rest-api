package com.specialized.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="gps_point")
public class GpsPoint extends ModelBase {

    @Id 
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gps_points_seq_gen")
    @SequenceGenerator(name = "gps_points_seq_gen", sequenceName = "sbc_gps_point_id_seq", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name="time_stamp")
    private Long timeStamp;

    @Column(name="lat")
    private float lat;
    
    @Column(name="lon")
    private float lon;
    
    @Column(name="speed")
    private float speed;
    
    @Column(name="elevation")
    private float elevation;
    
    @Column(name="heading")
    private float heading;
    
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

    public float getLat() {
        return lat;
    }

    public void setLat(float lat) {
        this.lat = lat;
    }

    public float getLon() {
        return lon;
    }

    public void setLon(float lon) {
        this.lon = lon;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getElevation() {
        return elevation;
    }

    public void setElevation(float elevation) {
        this.elevation = elevation;
    }

    public float getHeading() {
        return heading;
    }

    public void setHeading(float heading) {
        this.heading = heading;
    }
}
