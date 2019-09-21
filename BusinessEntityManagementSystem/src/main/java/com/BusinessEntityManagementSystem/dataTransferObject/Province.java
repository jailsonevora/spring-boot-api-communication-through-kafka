package com.BusinessEntityManagementSystem.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Province {

    @JsonProperty("province")
    private String province;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private int status = 1;



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
}
