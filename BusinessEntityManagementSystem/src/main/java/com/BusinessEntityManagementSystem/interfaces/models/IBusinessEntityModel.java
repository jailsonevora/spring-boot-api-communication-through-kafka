package com.BusinessEntityManagementSystem.interfaces.models;

import java.util.Set;

public interface IBusinessEntityModel<IAddressModel, IPartnerModel, IStoreModel, IAffiliatedCompanyModel, IEconomicActivityCodeModel> {

    long getNaturalId();

    void setNaturalId(long naturalId);

    String getTaxId();

    void setTaxId(String taxId);

    int getStatus();

    void setStatus(int status);

    IAddressModel getAddress();

    void setAddress(IAddressModel address);

    Set<IPartnerModel> getPartner();

    void setPartner(Set<IPartnerModel> partner);

    Set<IStoreModel> getStore();

    void setStore(Set<IStoreModel> store);

    Set<IAffiliatedCompanyModel> getAffiliatedCompany();

    void setAffiliatedCompany(Set<IAffiliatedCompanyModel> affiliatedCompany);

    Set<IEconomicActivityCodeModel> getEconomicActivityCode();

    void setEconomicActivityCode(Set<IEconomicActivityCodeModel> economicActivityCode);
}
