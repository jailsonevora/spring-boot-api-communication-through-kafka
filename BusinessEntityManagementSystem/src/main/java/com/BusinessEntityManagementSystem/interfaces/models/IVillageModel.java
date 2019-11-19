package com.BusinessEntityManagementSystem.interfaces.models;

public interface IVillageModel<ICommuneModel> {

    String getVillage();

    void setVillage(String village);

    String getDescription();

    void setDescription(String description);

    int getStatus();

    void setStatus(int status);

    ICommuneModel getCommune();

    void setCommune(ICommuneModel commune);
}
