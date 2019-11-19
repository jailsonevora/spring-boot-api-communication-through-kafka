package com.BusinessStatisticsUnitFiles.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
abstract class AuditModel<U> implements Serializable {

    @JsonProperty("createdBy")
    private U createdBy;

    @JsonProperty("lastModifiedBy")
    private U lastModifiedBy;

    @JsonProperty("approvedBy")
    private U approvedBy;

    @JsonProperty("renewedBy")
    private U renewedBy;

    @JsonProperty("createdAt")
    private Date createdAt;

    @JsonProperty("updatedAt")
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

    //endregion
}

