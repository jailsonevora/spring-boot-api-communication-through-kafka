package com.BusinessEntityManagementSystem.dataTransferObject;

import com.BusinessEntityManagementSystem.interfaces.models.ICountyModel;
import com.BusinessEntityManagementSystem.interfaces.models.IProvinceModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class County extends AuditModel<String> implements ICountyModel<IProvinceModel>, Serializable {

    @JsonProperty("county")
    private String county;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private int status = 1;

    @JsonProperty("province")
    private IProvinceModel province;



    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public IProvinceModel getProvince() {
        return province;
    }

    public void setProvince(IProvinceModel province) {
        this.province = province;
    }
}
