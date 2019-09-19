package com.BusinessEntityManagementSystem.models;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(
        value = {"createdAt", "updatedAt"},
        allowGetters = true
)
abstract class AuditModel<U> implements Serializable {

    @CreatedBy
    @Column(name = "CREATED_BY")
    private U createdBy;

    @Column(name = "UPDATED_BY")
    @LastModifiedBy
    private U lastModifiedBy;

    @Column(name = "APPROVED_BY", updatable = false)
    @LastModifiedBy
    private U approvedBy;

    @Column(name = "RENEWED_BY", updatable = false)
    @LastModifiedBy
    private U renewedBy;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATED_AT", nullable = false, updatable = false)
    @CreatedDate
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATED_AT", nullable = false)
    @LastModifiedDate
    private Date updatedAt;


    public U getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(U createdBy) {
        this.createdBy = createdBy;
    }

    public U getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(U lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public U getApprovedBy() {
        return approvedBy;
    }

    public void setApprovedBy(U approvedBy) {
        this.approvedBy = approvedBy;
    }

    public U getRenewedBy() {
        return renewedBy;
    }

    public void setRenewedBy(U renewedBy) {
        this.renewedBy = renewedBy;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    //@JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    //@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
    //endregion
}
