package com.BusinessEntityManagementSystem.dataTransferObject;


import com.BusinessEntityManagementSystem.interfaces.models.IAffiliatedCompanyModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AffiliatedCompany implements IAffiliatedCompanyModel, Serializable {

    @JsonProperty("percentageShared")
    private float percentageShared;

    @JsonProperty("integrationDate")
    private java.util.Date integrationDate;

    @JsonProperty("status")
    private int status = 1;



    public float getPercentageShared() { return percentageShared; }

    public void setPercentageShared(float percentageShared) { this.percentageShared = percentageShared; }

    public Date getIntegrationDate() { return integrationDate; }

    public void setIntegrationDate(Date integrationDate) { this.integrationDate = integrationDate; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }
}
