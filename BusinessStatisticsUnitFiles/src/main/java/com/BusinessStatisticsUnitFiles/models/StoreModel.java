package com.BusinessStatisticsUnitFiles.models;

import com.BusinessStatisticsUnitFiles.interfaces.models.IStoreModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="BSUF_Store")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class StoreModel<IEconomicActivityCodeModel extends  EconomicActivityCodeModel, IBusinessEntityModel extends BusinessEntityModel> extends AuditModel<String> implements IStoreModel, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID_Store")
    @JsonIgnore
    private long id;

    @Column(name = "Name")
    @Size(max=50)
    private String name;

    @Column(name = "Status")
    private int status = 1;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.ALL
            },
            targetEntity= EconomicActivityCodeModel.class)
    @JoinTable(name = "BSUF_Store_EconomicActivityCode",
            joinColumns = { @JoinColumn(name = "ID_Store") },
            inverseJoinColumns = { @JoinColumn(name = "ID_EconomicActivityCode") })
    private Set<IEconomicActivityCodeModel> economicActivityCode = new HashSet<>();

/*
	@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			},
			mappedBy = "store")
	@JsonIgnore
	private Set<IBusinessEntityModel> businessEntity = new HashSet<>();

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "businessEntity", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	//@JsonIgnore
	private IBusinessEntityModel businessEntity;*/


    public long getStoreId() { return id; }

    public void setStoreId(long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }
}
