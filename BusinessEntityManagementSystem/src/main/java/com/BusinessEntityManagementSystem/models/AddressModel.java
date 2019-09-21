package com.BusinessEntityManagementSystem.models;

import com.BusinessEntityManagementSystem.interfaces.models.IAddressModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="BEMS_Address")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AddressModel extends AuditModel<String> implements IAddressModel, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID_Address")
    @JsonIgnore
    private long id;

    @Column(name = "Email")
    @Email
    @Size(max=60)
    private String email;

    @Column(name = "Address")
    @Size(max=120)
    private String address;

    @Column(name = "Status")
    private int status = 1;



    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getAddress() { return address; }

    public void setAddress(String address) { this.address = address; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }
}
