package com.BusinessEntityManagementSystem.models;

import com.BusinessEntityManagementSystem.interfaces.models.ICountryModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="BEMS_Country")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class CountryModel extends AuditModel<String> implements ICountryModel, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID_Country")
    //@JsonIgnore
    private long id;

    @Column(name = "Country")
    private String country;

    @Column(name = "Status")
    private int status = 1;

   /*@OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "country")
	@JsonIgnore
    private Set<IProvinceModel> province = new HashSet<>();*/

    //region accessors for public property

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) { this.status = status; }

    //endregion
}
