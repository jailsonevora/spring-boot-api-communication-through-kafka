package com.BusinessEntityManagementSystem.interfaces.models;

public interface IProvinceModel<ICountryModel> {

    String getProvince();

    void setProvince(String province);

    String getDescription();

    void setDescription(String description);

    int getStatus();

    void setStatus(int status);

    ICountryModel getCountry();

    void setCountry(ICountryModel country);
}
