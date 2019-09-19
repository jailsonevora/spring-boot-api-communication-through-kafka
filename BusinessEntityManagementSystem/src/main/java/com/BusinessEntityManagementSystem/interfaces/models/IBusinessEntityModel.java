package com.BusinessEntityManagementSystem.interfaces.models;

public interface IBusinessEntityModel {

    long getNaturalId();

    void setNaturalId(long naturalId);

    String getTaxId();

    void setTaxId(String taxId);

    int getStatus();

    void setStatus(int status);
}
