package com.BusinessEntityManagementSystem.models;

import com.BusinessEntityManagementSystem.interfaces.models.ICountryModel;
import com.BusinessEntityManagementSystem.interfaces.models.ICountyModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="BEMS_County")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CountyModel <IProvinceModel extends ProvinceModel> extends AuditModel<String> implements ICountyModel<IProvinceModel>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID_County")
    //@JsonIgnore
    private long id;

    @Column(name = "County")
    @Size(max=4)
    private String county;

    @Column(name = "Description")
    @Size(max=50)
    private String description;

    @Column(name = "Status")
    private int status = 1;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity=ProvinceModel.class)
    @JoinColumn(name = "Province", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    //@JsonIgnore
    private IProvinceModel province;

    //region accessors for public property

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
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

    public IProvinceModel getProvince() {
        return province;
    }

    public void setProvince(IProvinceModel province) {
        this.province = province;
    }

    //endregion
}
