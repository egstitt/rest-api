package com.estitt.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="app_config")
public class AppConfig extends ModelBase {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "app_configs_seq_gen")
    @SequenceGenerator(name = "app_configs_seq_gen", sequenceName = "app_config_id_seq", allocationSize = 1, initialValue = 1)
    private Long id;

    @NotNull
    @Column(name="version_number")
    private String versionNumber;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(String versionNumber) {
        this.versionNumber = versionNumber;
    }
}
