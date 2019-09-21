package com.BusinessEntityManagementSystem.models;

import com.BusinessEntityManagementSystem.interfaces.models.IAcademicDegreeModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name="BEMS_AcademicDegree")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AcademicDegreeModel extends AuditModel<String> implements IAcademicDegreeModel, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "ID_AcademicDegree")
    @JsonIgnore
    private long id;

    @Column(name = "Degree")
    @Size(max=50)
    private String degree;

    @Column(name = "Status")
    private int status = 1;


    public long getId() { return id; }

    public void setId(long id) { this.id = id; }

    public String getDegree() { return degree; }

    public void setDegree(String name) { this.degree = degree; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }
}
