package com.BusinessStatisticsUnitFiles.models;

import com.BusinessStatisticsUnitFiles.interfaces.models.IAffiliatedCompanyModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="BSUF_AffiliatedCompany")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AffiliatedCompanyModel<IBusinessEntityModel extends BusinessEntityModel> extends AuditModel<String> implements IAffiliatedCompanyModel, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID_AffiliatedCompany")
    @JsonIgnore
    private long id;

    @Column(name = "PercentageShared")
    private float percentageShared;

    @Column(name = "IntegrationDate")
    @Temporal(TemporalType.TIMESTAMP)
    private java.util.Date integrationDate;

    @Column(name = "Status")
    private int status = 1;


    /*@ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "affiliatedCompany")
    @JsonIgnore
    private Set<IBusinessEntityModel> businessEntity = new HashSet<>();*/



    public long getAffiliatedCompanyId() { return id; }

    public void setAffiliatedCompanyId(long id) { this.id = id; }

    public float getPercentageShared() { return percentageShared; }

    public void setPercentageShared(float percentageShared) { this.percentageShared = percentageShared; }

    public Date getIntegrationDate() { return integrationDate; }

    public void setIntegrationDate(Date integrationDate) { this.integrationDate = integrationDate; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }
}
