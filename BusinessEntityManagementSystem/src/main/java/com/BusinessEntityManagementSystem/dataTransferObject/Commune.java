package com.BusinessEntityManagementSystem.dataTransferObject;

import com.BusinessEntityManagementSystem.interfaces.models.ICommuneModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Commune extends AuditModel<String> implements ICommuneModel, Serializable {

    @JsonProperty("commune")
    private String commune;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private int status = 1;

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
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
