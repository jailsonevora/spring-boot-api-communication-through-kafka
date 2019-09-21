package com.BusinessEntityManagementSystem.models;

import com.BusinessEntityManagementSystem.interfaces.models.IPartnerModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="BEMS_Partner")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PartnerModel<IBusinessEntityModel extends  BusinessEntityModel> extends AuditModel<String> implements IPartnerModel, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID_Partner")
    @JsonIgnore
    private long id;

    @Column(name = "Name")
    @Size(max=50)
    private String name;

    @Column(name = "Num")
    private float num;

    @Column(name = "Status")
    private int status = 1;


    /*@ManyToMany(fetch = FetchType.LAZY,
			cascade = {
					CascadeType.PERSIST,
					CascadeType.MERGE
			},
			mappedBy = "partner")
	@JsonIgnore
	private Set<IBusinessEntityModel> businessEntity = new HashSet<>();*/


    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public float getNum() { return num; }

    public void setNum(float num) { this.num = num; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }
}
