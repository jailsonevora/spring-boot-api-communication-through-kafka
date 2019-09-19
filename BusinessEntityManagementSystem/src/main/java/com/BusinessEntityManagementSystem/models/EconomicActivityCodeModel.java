package com.BusinessEntityManagementSystem.models;

import com.BusinessEntityManagementSystem.interfaces.models.IEconomicActivityCodeModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="BSUF_EconomicActivityCode")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class EconomicActivityCodeModel<IBusinessEntityModel extends BusinessEntityModel> extends AuditModel<String> implements IEconomicActivityCodeModel, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID_EconomicActivityCode")
    @JsonIgnore
    private long id;

    @Column(name = "EconomicActivityCode")
    @Size(max=50)
    private String economicActivityCode;

    @Column(name = "Description")
    @Size(max=200)
    private String description;

    @Column(name = "Status")
    private int status = 1;


	/*@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			},
			mappedBy = "economicActivityCode")
	@JsonIgnore
	private Set<IStoreModel> store = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					//CascadeType.PERSIST,
					CascadeType.MERGE
			},
			mappedBy = "economicActivityCode")
	@JsonIgnore
	private Set<IBusinessEntityModel> businessEntity = new HashSet<>();*/


    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getEconomicActivityCode() { return economicActivityCode; }

    public void setEconomicActivityCode(String economicActivityCode) { this.economicActivityCode = economicActivityCode; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }
}
