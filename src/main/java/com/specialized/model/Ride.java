package com.specialized.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="ride")
public class Ride extends ModelBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rides_seq_gen")
    @SequenceGenerator(name = "rides_seq_gen", sequenceName = "sbc_ride_id_seq", allocationSize = 1, initialValue = 1)
    private Long id;
    
    @Column(name="date_property")
    private Long dateProperty;
    
    @Column(name="mac_address")
    private String macAddress;

    @Transient
    private List<GpsPoint> gpsData = new ArrayList<GpsPoint>();

    @Transient
    private List<AccPoint> accData = new ArrayList<AccPoint>();
    
    @Transient
    private List<GyroPoint> gyroData = new ArrayList<GyroPoint>();
    
    @Transient
    private List<ReedPoint> reedData = new ArrayList<ReedPoint>();
    
    public Long getDateProperty() {
        return dateProperty;
    }

    public void setDateProperty(Long dateProperty) {
        this.dateProperty = dateProperty;
    }

    public String getMacAddress() {
        return macAddress;
    }

    public void setMacAddress(String macAddress) {
        this.macAddress = macAddress;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @JsonIgnore
    public List<GpsPoint> getGpsData() {
        return gpsData;
    }

    @JsonProperty
    public void setGpsData(List<GpsPoint> gpsData) {
        this.gpsData = gpsData;
    }

    @JsonIgnore
    public List<AccPoint> getAccData() {
        return accData;
    }

    @JsonProperty
    public void setAccData(List<AccPoint> accData) {
        this.accData = accData;
    }

    @JsonIgnore
    public List<GyroPoint> getGyroData() {
        return gyroData;
    }

    @JsonProperty
    public void setGyroData(List<GyroPoint> gyroData) {
        this.gyroData = gyroData;
    }

    @JsonIgnore
    public List<ReedPoint> getReedData() {
        return reedData;
    }

    @JsonProperty
    public void setReedData(List<ReedPoint> reedData) {
        this.reedData = reedData;
    }
}
