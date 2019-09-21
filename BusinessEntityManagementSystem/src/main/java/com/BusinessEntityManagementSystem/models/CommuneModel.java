package com.BusinessEntityManagementSystem.models;

import com.BusinessEntityManagementSystem.interfaces.models.ICommuneModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="BEMS_Commune")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CommuneModel <ICountyModel extends CountyModel> extends AuditModel<String> implements ICommuneModel, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID_Commune")
    //@JsonIgnore
    private long id;

    @Column(name = "Commune")
    @Size(max=4)
    private String commune;

    @Column(name = "Description")
    @Size(max=50)
    private String description;

    @Column(name = "Status")
    private int status = 1;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity=CountyModel.class)
    @JoinColumn(name = "County", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    //@JsonIgnore
    private ICountyModel county;

    //region accessors for public property

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String code) {
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

    public void setStatus(int status) { this.status = status; }

    public ICountyModel getCounty() {
        return county;
    }

    public void setCounty(ICountyModel county) {
        this.county = county;
    }

    //endregion
}

