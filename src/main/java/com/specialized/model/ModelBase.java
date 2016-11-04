package com.specialized.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonFormat;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class ModelBase {

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss'Z'", timezone="GMT")
    @Column(name="create_date")
    private Date createDate;
    
    @CreatedBy
    @Column(name="create_account")
    private Long createAccount;

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy'T'HH:mm:ss'Z'", timezone="GMT")
    @Column(name="update_date")
    private Date updateDate;
    
    @LastModifiedBy
    @Column(name="update_account")
    private Long updateAccount;
    
    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Long getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(Long createAccount) {
        this.createAccount = createAccount;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Long getUpdateAccount() {
        return updateAccount;
    }

    public void setUpdateAccount(Long updateAccount) {
        this.updateAccount = updateAccount;
    }
}