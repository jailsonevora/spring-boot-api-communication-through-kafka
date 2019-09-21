package com.BusinessEntityManagementSystem.interfaces.models;

public interface ICommuneModel {

    String getCommune();

    void setCommune(String code);

    String getDescription();

    void setDescription(String description);

    int getStatus();

    void setStatus(int status);
}
