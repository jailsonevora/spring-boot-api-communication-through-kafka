package com.BusinessEntityManagementSystem.dataTransferObject;

import com.BusinessEntityManagementSystem.interfaces.models.IAddressModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Address extends AuditModel<String> implements IAddressModel, Serializable {

    @JsonProperty("email")
    private String email;

    @JsonProperty("address")
    private String address;

    @JsonProperty("status")
    private int status = 1;



    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }
}
