package com.BusinessEntityManagementSystem.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Country {

    @JsonProperty("country")
    private String country;

    @JsonProperty("status")
    private int status = 1;


    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
