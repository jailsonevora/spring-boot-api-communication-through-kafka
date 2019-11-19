package com.BusinessEntityManagementSystem.dataTransferObject;

import com.BusinessEntityManagementSystem.interfaces.models.ICommuneModel;
import com.BusinessEntityManagementSystem.interfaces.models.IVillageModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Village extends AuditModel<String> implements IVillageModel<ICommuneModel>, Serializable {

    @JsonProperty("village")
    private String village;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status")
    private int status = 1;

    @JsonProperty("commune")
    private ICommuneModel commune;



    public String getVillage() {
        return village;
    }

    public void setVillage(String village) {
        this.village = village;
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

    public ICommuneModel getCommune() { return commune; }

    public void setCommune(ICommuneModel commune) { this.commune = commune; }
}
