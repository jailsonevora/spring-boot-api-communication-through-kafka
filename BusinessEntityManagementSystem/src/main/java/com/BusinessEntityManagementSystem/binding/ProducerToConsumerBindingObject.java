package com.BusinessEntityManagementSystem.binding;

import com.BusinessEntityManagementSystem.dataAccessObject.*;
import com.BusinessEntityManagementSystem.dataTransferObject.BusinessEntity;
import com.BusinessEntityManagementSystem.models.BusinessEntityModel;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class ProducerToConsumerBindingObject {

    private final IAddressRepository addressRepository;
    private final IPartnerRepository partnerRepository;
    private final IStoreRepository storeRepository;
    private final IAffiliatedCompanyRepository affiliatedCompanyRepository;
    private final IEconomicActivityCodeRepository economicActivityCodeRepository;

    @Autowired
    public ProducerToConsumerBindingObject(IAddressRepository addressRepository, IPartnerRepository partnerRepository, IStoreRepository storeRepository,
                                           IAffiliatedCompanyRepository affiliatedCompanyRepository, IEconomicActivityCodeRepository economicActivityCodeRepository) {
        this.addressRepository = addressRepository;
        this.partnerRepository = partnerRepository;
        this.storeRepository = storeRepository;
        this.affiliatedCompanyRepository = affiliatedCompanyRepository;
        this.economicActivityCodeRepository = economicActivityCodeRepository;
    }

    public BusinessEntity BindingWithObjectMapper(BusinessEntity businessEntityDTO,
                                  BusinessEntityModel businessEntityModel) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        com.BusinessEntityManagementSystem.dataTransferObject.BusinessEntity result = mapper.readValue(businessEntityModel.toString(), BusinessEntity.class);

        return businessEntityDTO;
    }

    public BusinessEntity BindingByHardCode(BusinessEntity businessEntityDTO,
                                            BusinessEntityModel businessEntityModel){

        businessEntityDTO.setAddress(businessEntityModel.getAddress());
        businessEntityDTO.setAffiliatedCompany(businessEntityModel.getAffiliatedCompany());
        businessEntityDTO.setEconomicActivityCode(businessEntityModel.getEconomicActivityCode());
        businessEntityDTO.setNaturalId(businessEntityModel.getNaturalId());
        businessEntityDTO.setPartner(businessEntityModel.getPartner());
        businessEntityDTO.setStatus(businessEntityModel.getStatus());
        businessEntityDTO.setStore(businessEntityModel.getStore());
        businessEntityDTO.setTaxId(businessEntityModel.getTaxId());
        businessEntityDTO.setCreatedAt(businessEntityModel.getCreatedAt());
        businessEntityDTO.setUpdatedAt(businessEntityModel.getUpdatedAt());
        businessEntityDTO.setCreatedBy(businessEntityModel.getCreatedBy().toString());
        businessEntityDTO.setLastModifiedBy(businessEntityModel.getLastModifiedBy().toString());
        businessEntityDTO.setApprovedBy(businessEntityModel.getApprovedBy().toString());
        businessEntityDTO.setRenewedBy(businessEntityModel.getRenewedBy().toString());

        return businessEntityDTO;
    }
}
