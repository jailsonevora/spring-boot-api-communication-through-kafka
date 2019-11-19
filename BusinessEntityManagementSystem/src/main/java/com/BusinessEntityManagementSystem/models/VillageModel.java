package com.BusinessEntityManagementSystem.models;

import com.BusinessEntityManagementSystem.interfaces.models.IVillageModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="BEMS_Village")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class VillageModel <ICommuneModel extends CountyModel> extends AuditModel<String> implements IVillageModel<ICommuneModel>, Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID_Village")
    //@JsonIgnore
    private long id;

    @Column(name = "Village")
    @Size(max=4)
    private String village;

    @Column(name = "Description")
    @Size(max=50)
    private String description;

    @Column(name = "Status")
    private int status = 1;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity=CommuneModel.class)
    @JoinColumn(name = "Commune", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    //@JsonIgnore
    private ICommuneModel commune;

    //region accessors for public property

    public void setId(long id) { this.id = id; }

    public long getId() { return id; }

    public String getVillage() { return village; }

    public void setVillage(String village) { this.village = village; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }

    public ICommuneModel getCommune() { return commune; }

    public void setCommune(ICommuneModel commune) { this.commune = commune; }

    //endregion
}

