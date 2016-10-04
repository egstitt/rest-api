package com.specialized.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
public class ModelBase {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss'Z'", timezone="GMT")
    @Column(name="create_date")
    private Date createDate;
    
    @Column(name="create_user")
    private Long createUser;

    @PrePersist
    public void onCreate() {
        this.createDate = new Date();
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateUser() {
        return createUser;
    }

    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }
}