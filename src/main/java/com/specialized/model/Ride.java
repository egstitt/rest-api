package com.specialized.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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
}
