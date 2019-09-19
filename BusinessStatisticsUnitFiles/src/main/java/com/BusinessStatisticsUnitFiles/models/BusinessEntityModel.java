package com.BusinessStatisticsUnitFiles.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.BusinessStatisticsUnitFiles.interfaces.models.IBusinessEntityModel;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="BSUF_BusinessEntity")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BusinessEntityModel<IAddressModel extends AddressModel, IPartnerModel extends PartnerModel, IStoreModel extends StoreModel, IAffiliatedCompanyModel extends AffiliatedCompanyModel, IEconomicActivityCodeModel extends EconomicActivityCodeModel> extends AuditModel<String> implements IBusinessEntityModel, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID_BusinessEntity", updatable = false, nullable = false)
    private long id;

    //@NaturalId
    //@NotEmpty
    //@NotNull
    @Column(name = "NATURAL_ID", updatable = false, nullable = false)
    private long naturalId;

    @Column(name = "TaxId")
    @Size(min = 1, max = 50)
    private String taxId;

    @Column(name = "Status")
    private int status = 1;


    // region OneToOne

    @OneToOne(fetch = FetchType.EAGER, cascade =  CascadeType.ALL, targetEntity= AddressModel.class)
    @JoinColumn(name = "Address", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
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
    private Set<IPartnerModel> partner = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.ALL
            },
            targetEntity= StoreModel.class)
    @JoinTable(name = "BSUF_BusinessEntity_Store",
            joinColumns = { @JoinColumn(name = "ID_BusinessEntity") },
            inverseJoinColumns = { @JoinColumn(name = "ID_Store") })
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
    private Set<IAffiliatedCompanyModel> affiliatedCompany = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.ALL
            },
            targetEntity= EconomicActivityCodeModel.class)
    @JoinTable(name = "BSUF_BusinessEntity_EconomicActivityCode",
            joinColumns = { @JoinColumn(name = "ID_BusinessEntity") },
            inverseJoinColumns = { @JoinColumn(name = "ID_EconomicActivityCode") })
    private Set<IEconomicActivityCodeModel> economicActivityCode = new HashSet<>();

    // endregion ManyToMany



    public long getBusinessId() { return id; }

    public void setBusinessId(long id) { this.id = id; }

    public long getNaturalId() { return naturalId; }

    public void setNaturalId(long naturalId) { this.naturalId = naturalId; }

    public String getTaxId() { return taxId; }

    public void setTaxId(String taxId) { this.taxId = taxId; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }
}
