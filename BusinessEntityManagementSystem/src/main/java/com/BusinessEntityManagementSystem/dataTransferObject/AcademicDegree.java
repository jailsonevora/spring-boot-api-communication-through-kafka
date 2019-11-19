package com.BusinessEntityManagementSystem.dataTransferObject;

import com.BusinessEntityManagementSystem.interfaces.models.IAcademicDegreeModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AcademicDegree extends AuditModel<String> implements IAcademicDegreeModel, Serializable {

    @JsonProperty("degree")
    private String degree;

    @JsonProperty("status")
    private int status = 1;



    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
