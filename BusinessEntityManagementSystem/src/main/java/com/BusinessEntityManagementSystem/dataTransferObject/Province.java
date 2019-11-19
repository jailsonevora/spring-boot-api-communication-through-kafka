package com.BusinessEntityManagementSystem.dataTransferObject;

import com.BusinessEntityManagementSystem.interfaces.models.ICountryModel;
import com.BusinessEntityManagementSystem.interfaces.models.IProvinceModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Province extends AuditModel<String> implements IProvinceModel<ICountryModel>, Serializable {

    @JsonProperty("province")
    private String province;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private int status = 1;

    @JsonProperty("country")
    private ICountryModel country;



    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public ICountryModel getCountry() {
        return country;
    }

    public void setCountry(ICountryModel country) {
        this.country = country;
    }
}
