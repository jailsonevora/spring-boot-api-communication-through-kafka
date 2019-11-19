package com.BusinessStatisticsUnitFiles.dataTransferObject;

import com.BusinessStatisticsUnitFiles.interfaces.models.IEconomicActivityCodeModel;
import com.BusinessStatisticsUnitFiles.interfaces.models.IStoreModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Store extends AuditModel<String> implements IStoreModel<IEconomicActivityCodeModel>, Serializable {

    @JsonProperty("name")
    private String name;

    @JsonProperty("status")
    private int status = 1;

    @JsonProperty("economicActivityCode")
    private Set<IEconomicActivityCodeModel> economicActivityCode = new HashSet<>();


    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }

    public Set<IEconomicActivityCodeModel> getEconomicActivityCode() {
        return economicActivityCode;
    }

    public void setEconomicActivityCode(Set<IEconomicActivityCodeModel> economicActivityCode) {
        this.economicActivityCode = economicActivityCode;
    }
}
