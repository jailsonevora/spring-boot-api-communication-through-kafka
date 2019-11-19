package com.BusinessEntityManagementSystem.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.BusinessEntityManagementSystem.interfaces.models.IBusinessEntityModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="BEMS_BusinessEntity")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BusinessEntityModel<IAddressModel extends AddressModel, IPartnerModel extends PartnerModel, IStoreModel extends StoreModel, IAffiliatedCompanyModel extends AffiliatedCompanyModel, IEconomicActivityCodeModel extends EconomicActivityCodeModel> extends AuditModel<String>
        implements IBusinessEntityModel<IAddressModel, IPartnerModel, IStoreModel, IAffiliatedCompanyModel, IEconomicActivityCodeModel>, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID_BusinessEntity", updatable = false, nullable = false)
    //@JsonIgnore
    private long id;

    //@NaturalId
    //@NotEmpty
    //@NotNull
    @Column(name = "NATURAL_ID", updatable = false, nullable = false)
    @JsonProperty("naturalId")
    private long naturalId;

    @Column(name = "TaxId")
    @Size(min = 1, max = 50)
    @JsonProperty("taxId")
    private String taxId;

    @Column(name = "Status")
    @JsonProperty("status")
    private int status = 1;


    // region OneToOne

    @OneToOne(fetch = FetchType.EAGER, cascade =  CascadeType.ALL, targetEntity= AddressModel.class)
    @JoinColumn(name = "Address", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonProperty("address")
    private IAddressModel address;

    // endregion OneToOne


    // region ManyToMany

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.ALL
            },
            targetEntity= PartnerModel.class
    )
    @JoinTable(name = "BSUF_BusinessEntity_Partner",
            joinColumns = { @JoinColumn(name = "ID_BusinessEntity") },
            inverseJoinColumns = { @JoinColumn(name = "ID_Partner") })
    @JsonProperty("partner")
    private Set<IPartnerModel> partner = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.ALL
            },
            targetEntity= StoreModel.class)
    @JoinTable(name = "BSUF_BusinessEntity_Store",
            joinColumns = { @JoinColumn(name = "ID_BusinessEntity") },
            inverseJoinColumns = { @JoinColumn(name = "ID_Store") })
    @JsonProperty("store")
    private Set<IStoreModel> store = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.ALL
            },
            targetEntity= AffiliatedCompanyModel.class
    )
    @JoinTable(name = "BSUF_BusinessEntity_AffiliatedCompany",
            joinColumns = { @JoinColumn(name = "ID_BusinessEntity") },
            inverseJoinColumns = { @JoinColumn(name = "ID_AffiliatedCompany") })
    @JsonProperty("affiliatedCompany")
    private Set<IAffiliatedCompanyModel> affiliatedCompany = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.ALL
            },
            targetEntity= EconomicActivityCodeModel.class)
    @JoinTable(name = "BSUF_BusinessEntity_EconomicActivityCode",
            joinColumns = { @JoinColumn(name = "ID_BusinessEntity") },
            inverseJoinColumns = { @JoinColumn(name = "ID_EconomicActivityCode") })
    @JsonProperty("economicActivityCode")
    private Set<IEconomicActivityCodeModel> economicActivityCode = new HashSet<>();

    // endregion ManyToMany

    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public long getNaturalId() { return naturalId; }

    public void setNaturalId(long naturalId) { this.naturalId = naturalId; }

    public String getTaxId() { return taxId; }

    public void setTaxId(String taxId) { this.taxId = taxId; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }

    public IAddressModel getAddress() {
        return address;
    }

    public void setAddress(IAddressModel address) {
        this.address = address;
    }

    public Set<IPartnerModel> getPartner() {
        return partner;
    }

    public void setPartner(Set<IPartnerModel> partner) {
        this.partner = partner;
    }

    public Set<IStoreModel> getStore() {
        return store;
    }

    public void setStore(Set<IStoreModel> store) {
        this.store = store;
    }

    public Set<IAffiliatedCompanyModel> getAffiliatedCompany() {
        return affiliatedCompany;
    }

    public void setAffiliatedCompany(Set<IAffiliatedCompanyModel> affiliatedCompany) {
        this.affiliatedCompany = affiliatedCompany;
    }

    public Set<IEconomicActivityCodeModel> getEconomicActivityCode() {
        return economicActivityCode;
    }

    public void setEconomicActivityCode(Set<IEconomicActivityCodeModel> economicActivityCode) {
        this.economicActivityCode = economicActivityCode;
    }
}
