package com.BusinessEntityManagementSystem.dataTransferObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Store {

    @JsonProperty("name")
    private String name;

    @JsonProperty("status")
    private int status = 1;


    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }
}
