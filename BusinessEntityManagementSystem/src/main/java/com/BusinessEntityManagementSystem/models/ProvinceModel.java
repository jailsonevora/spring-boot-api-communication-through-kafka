package com.BusinessEntityManagementSystem.models;

import com.BusinessEntityManagementSystem.interfaces.models.IProvinceModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="BEMS_Province")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class ProvinceModel <ICountryModel extends CountryModel> extends AuditModel<String> implements IProvinceModel<ICountryModel>, Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID_Province")
    //@JsonIgnore
    private long id;

    @Column(name = "Province")
    @Size(max=4)
    private String province;

    @Column(name = "Description")
    @Size(max=50)
    private String description;

    @Column(name = "Status")
    private int status = 1;

    @ManyToOne(fetch = FetchType.EAGER, optional = false, targetEntity=CountryModel.class)
    @JoinColumn(name = "Country", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    //@JsonIgnore
    private ICountryModel country;

    //region accessors for public property

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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

    public ICountryModel getCountry() {
        return country;
    }

    public void setCountry(ICountryModel country) {
        this.country = country;
    }

    //endregion
}
