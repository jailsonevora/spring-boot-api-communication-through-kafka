package com.BusinessStatisticsUnitFiles.dataTransferObject;

import com.BusinessStatisticsUnitFiles.interfaces.models.IPartnerModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Partner extends AuditModel<String> implements IPartnerModel, Serializable {

    @JsonProperty("name")
    private String name;

    @JsonProperty("num")
    private float num;

    @JsonProperty("status")
    private int status = 1;


    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public float getNum() { return num; }

    public void setNum(float num) { this.num = num; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }
}
