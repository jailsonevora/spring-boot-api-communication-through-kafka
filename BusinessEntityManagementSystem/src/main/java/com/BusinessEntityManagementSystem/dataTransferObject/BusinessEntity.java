package com.BusinessEntityManagementSystem.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BusinessEntity {

    @JsonProperty("naturalId")
    private long naturalId;

    @JsonProperty("taxId")
    private String taxId;

    @JsonProperty("status")
    private int status = 1;



    public long getNaturalId() { return naturalId; }

    public void setNaturalId(long naturalId) { this.naturalId = naturalId; }

    public String getTaxId() { return taxId; }

    public void setTaxId(String taxId) { this.taxId = taxId; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }
}
