package com.BusinessEntityManagementSystem.dataTransferObject;

import com.BusinessEntityManagementSystem.interfaces.models.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/*@JsonInclude(JsonInclude.Include.NON_NULL)*/
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BusinessEntity<IAddressModel, IPartnerModel, IStoreModel, IAffiliatedCompanyModel, IEconomicActivityCodeModel>
        extends AuditModel<String> implements IBusinessEntityModel<IAddressModel, IPartnerModel, IStoreModel, IAffiliatedCompanyModel, IEconomicActivityCodeModel>, Serializable {

    @JsonProperty("naturalId")
    //@JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    private long naturalId;

    @JsonProperty("taxId")
    private String taxId;

    @JsonProperty("status")
    private int status = 1;

    @JsonProperty("address")
    private IAddressModel address;

    @JsonProperty("partner")
    private Set<IPartnerModel> partner = new HashSet<>();

    @JsonProperty("store")
    private Set<IStoreModel> store = new HashSet<>();

    @JsonProperty("affiliatedCompany")
    private Set<IAffiliatedCompanyModel> affiliatedCompany = new HashSet<>();

    @JsonProperty("economicActivityCode")
    private Set<IEconomicActivityCodeModel> economicActivityCode = new HashSet<>();

    //@JsonProperty("country")
    //private String country;


    public long getNaturalId() { return naturalId; }

    public void setNaturalId(long naturalId) { this.naturalId = naturalId; }

    public String getTaxId() { return taxId; }

    public void setTaxId(String taxId) { this.taxId = taxId; }

    public int getStatus() { return status; }

    public void setStatus(int status) { this.status = status; }

    public IAddressModel getAddress() {
        return address;
    }

    public void setAddress(IAddressModel address) {
        this.address = address;
    }

    public Set<IPartnerModel> getPartner() {
        return partner;
    }

    public void setPartner(Set<IPartnerModel> partner) {
        this.partner = partner;
    }

    public Set<IStoreModel> getStore() {
        return store;
    }

    public void setStore(Set<IStoreModel> store) {
        this.store = store;
    }

    public Set<IAffiliatedCompanyModel> getAffiliatedCompany() {
        return affiliatedCompany;
    }

    public void setAffiliatedCompany(Set<IAffiliatedCompanyModel> affiliatedCompany) {
        this.affiliatedCompany = affiliatedCompany;
    }

    public Set<IEconomicActivityCodeModel> getEconomicActivityCode() {
        return economicActivityCode;
    }

    public void setEconomicActivityCode(Set<IEconomicActivityCodeModel> economicActivityCode) {
        this.economicActivityCode = economicActivityCode;
    }

    /*public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }*/
}
