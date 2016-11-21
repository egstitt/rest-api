package com.specialized.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

@Entity
@Table(name="bike_settings")
public class BikeSettings extends ModelBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bike_settings_seq_gen")
    @SequenceGenerator(name = "bike_settings_seq_gen", sequenceName = "bike_settings_id_seq", allocationSize = 1, initialValue = 1)
    private Long id;

    @Column(name="date_property")
    private Date dateProperty;
    
    @Column(name="rider")
    private String rider;
    
    @Column(name="rider_note")
    private String riderNote;
    
    @Column(name="accelerometer_rate")
    private Integer accelerometerRate;
    
    @Column(name="gyroscope_rate")
    private Integer gyroscopeRate;
    
    @Column(name="gps_rate")
    private Integer gpsRate;
    
    @Column(name="bike_names")
    private String bikeNames;
    
    @Column(name="bike_sizes")
    private String bikeSizes;
    
    @Column(name="wheel_names")
    private String wheelNames;
    
    @Column(name="tire_names")
    private String tireNames;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateProperty() {
        return dateProperty;
    }

    public void setDateProperty(Date dateProperty) {
        this.dateProperty = dateProperty;
    }

    public String getRider() {
        return rider;
    }

    public void setRider(String rider) {
        this.rider = rider;
    }

    public String getRiderNote() {
        return riderNote;
    }

    public void setRiderNote(String riderNote) {
        this.riderNote = riderNote;
    }

    public Integer getAccelerometerRate() {
        return accelerometerRate;
    }

    public void setAccelerometerRate(Integer accelerometerRate) {
        this.accelerometerRate = accelerometerRate;
    }

    public Integer getGyroscopeRate() {
        return gyroscopeRate;
    }

    public void setGyroscopeRate(Integer gyroscopeRate) {
        this.gyroscopeRate = gyroscopeRate;
    }

    public Integer getGpsRate() {
        return gpsRate;
    }

    public void setGpsRate(Integer gpsRate) {
        this.gpsRate = gpsRate;
    }

    public String[] getBikeNames() {
        if (bikeNames == null) 
            return new String[]{};
        return StringUtils.split(bikeNames, ",");
    }

    public void setBikeNames(String[] bikeNames) {
        this.bikeNames = StringUtils.join(bikeNames, ",");
    }

    public String[] getBikeSizes() {
        if (bikeSizes == null) 
            return new String[]{};
        return StringUtils.split(bikeSizes, ",");
    }

    public void setBikeSizes(String[] bikeSizes) {
        this.bikeSizes = StringUtils.join(bikeSizes, ",");
    }

    public String[] getWheelNames() {
        if (wheelNames == null) 
            return new String[]{};
        return StringUtils.split(wheelNames, ",");
    }

    public void setWheelNames(String[] wheelNames) {
        this.wheelNames = StringUtils.join(wheelNames, ",");
    }

    public String[] getTireNames() {
        if (tireNames == null) 
            return new String[]{};
        return StringUtils.split(tireNames, ",");
    }

    public void setTireNames(String[] tireNames) {
        this.tireNames = StringUtils.join(tireNames, ",");
    }
}
