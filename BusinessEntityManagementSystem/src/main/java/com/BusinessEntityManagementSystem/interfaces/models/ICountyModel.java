package com.BusinessEntityManagementSystem.interfaces.models;

public interface ICountyModel<IProvinceModel> {

    String getCounty();

    void setCounty(String county);

    String getDescription();

    void setDescription(String description);

    int getStatus();

    void setStatus(int status);

    IProvinceModel getProvince();

    void setProvince(IProvinceModel province);
}
