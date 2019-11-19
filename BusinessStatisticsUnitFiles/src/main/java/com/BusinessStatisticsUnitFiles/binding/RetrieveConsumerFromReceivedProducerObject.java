package com.BusinessStatisticsUnitFiles.binding;

import com.BusinessStatisticsUnitFiles.dataTransferObject.BusinessEntity;
import com.BusinessStatisticsUnitFiles.models.*;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RetrieveConsumerFromReceivedProducerObject {

    public static BusinessEntityModel Binding(BusinessEntityModel<AddressModel, PartnerModel, StoreModel, AffiliatedCompanyModel, EconomicActivityCodeModel> businessEntityModel,
                                              BusinessEntity<AddressModel, PartnerModel, StoreModel, AffiliatedCompanyModel, EconomicActivityCodeModel> businessEntityDTO){

        ObjectMapper mapper = new ObjectMapper();

        businessEntityModel.setTaxId(businessEntityDTO.getTaxId());
        businessEntityModel.setUpdatedAt(businessEntityDTO.getUpdatedAt());
        businessEntityModel.setNaturalId(businessEntityDTO.getNaturalId());
        businessEntityModel.setAddress(mapper.convertValue(businessEntityDTO.getAddress(), AddressModel.class));
        businessEntityModel.setAffiliatedCompany(businessEntityDTO.getAffiliatedCompany());
        businessEntityModel.setEconomicActivityCode(businessEntityDTO.getEconomicActivityCode());
        businessEntityModel.setPartner(businessEntityDTO.getPartner());
        businessEntityModel.setStatus(businessEntityDTO.getStatus());
        businessEntityModel.setStore(businessEntityDTO.getStore());
        businessEntityModel.setCreatedAt(businessEntityDTO.getCreatedAt());
        businessEntityModel.setCreatedBy(businessEntityDTO.getCreatedBy());
        businessEntityModel.setLastModifiedBy(businessEntityDTO.getLastModifiedBy());
        businessEntityModel.setRenewedBy(businessEntityDTO.getRenewedBy());
        businessEntityModel.setApprovedBy(businessEntityDTO.getApprovedBy());

        return businessEntityModel;

    }
}

