package com.BusinessEntityManagementSystem.interfaces.models;

import java.util.Set;

public interface IStoreModel<IEconomicActivityCodeModel> {


    String getName();

    void setName(String name);

    int getStatus();

    void setStatus(int status);

    Set<IEconomicActivityCodeModel> getEconomicActivityCode();

    void setEconomicActivityCode(Set<IEconomicActivityCodeModel> economicActivityCode);
}
