package com.BusinessEntityManagementSystem.v1.controllers;

import com.BusinessEntityManagementSystem.dataAccessObject.*;
import com.BusinessEntityManagementSystem.dataTransferObject.BusinessEntity;
import com.BusinessEntityManagementSystem.interfaces.resource.IGenericCRUD;
import com.BusinessEntityManagementSystem.models.BusinessEntityModel;
import com.Common.Util.Status;
import com.Common.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.util.Optional;

import com.BusinessEntityManagementSystem.binding.ProducerToConsumerBindingObject;
import com.BusinessEntityManagementSystem.kafka.KafkaProducer;

@RestController("BusinessEntityV1")
@RequestMapping("/api/businessEntityManagementSystem/v1")
@Api(value = "businessEntity")
public class BusinessEntityController implements IGenericCRUD<BusinessEntityModel> {

    @PersistenceContext
    private EntityManager entityManager;
    private final IBusinessEntityRepository businessEntityRepository;
    private final KafkaProducer kafkaProducer;
    private final IAddressRepository addressRepository;
    private final IPartnerRepository partnerRepository;
    private final IStoreRepository storeRepository;
    private final IAffiliatedCompanyRepository affiliatedCompanyRepository;
    private final IEconomicActivityCodeRepository economicActivityCodeRepository;

    @Autowired
    public BusinessEntityController(IBusinessEntityRepository businessEntityRepository, KafkaProducer kafkaProducer, IAddressRepository addressRepository, IPartnerRepository partnerRepository , IStoreRepository storeRepository,
                                    IAffiliatedCompanyRepository affiliatedCompanyRepository, IEconomicActivityCodeRepository economicActivityCodeRepository) {
        this.businessEntityRepository = businessEntityRepository;
        this.kafkaProducer = kafkaProducer;
        this.addressRepository = addressRepository;
        this.partnerRepository = partnerRepository;
        this.storeRepository = storeRepository;
        this.affiliatedCompanyRepository = affiliatedCompanyRepository;
        this.economicActivityCodeRepository = economicActivityCodeRepository;
    }

    @RequestMapping(value = "/businessEntity/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves given entity", response=BusinessEntityModel.class)
    public ResponseEntity<?> get(@Valid @PathVariable Long id){
        checkIfExist(id);
        return new ResponseEntity<> (businessEntityRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal()), HttpStatus.OK);
    }

    @RequestMapping(value="/businessEntity", method=RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves all the entities", response=BusinessEntityModel.class, responseContainer="List")
    public ResponseEntity<Page<BusinessEntityModel>> getAll(@Valid Pageable pageable) {
        return new ResponseEntity<>(businessEntityRepository.findAllByStatus(Status.PUBLISHED.ordinal(), pageable), HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value = "/businessEntity", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Creates a new entity", notes="The newly created entity Id will be sent in the location response header")
    public ResponseEntity<String> create(@Valid @RequestBody BusinessEntityModel newEntity) {

        // send to kafka
        ProducerToConsumerBindingObject entityToPublishToKafkaBroker = new ProducerToConsumerBindingObject(addressRepository, partnerRepository, storeRepository, affiliatedCompanyRepository, economicActivityCodeRepository);
        BusinessEntity result = entityToPublishToKafkaBroker.BindingByHardCode(new BusinessEntity(), newEntity);
        /*BusinessEntity result = null;
        try {
            result = entityToPublishToKafkaBroker.BindingWithObjectMapper(new BusinessEntity(), newEntity);
        } catch (IOException e) {
            e.printStackTrace();
        }*/


        if (result != null)
            kafkaProducer.send(result);

        // Set the location header for the newly created resource
        return new ResponseEntity <String>(null, getHttpHeaders(businessEntityRepository.save(newEntity)), HttpStatus.CREATED);
    }

    @Transactional
    @RequestMapping(value = "/businessEntity/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ApiOperation(value = "Updates given entity")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody BusinessEntityModel toUpdate){

        Optional<BusinessEntityModel> updatedOptionalClass = businessEntityRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal());
        if (updatedOptionalClass.isPresent()){

            BusinessEntityModel afterIsPresent = updatedOptionalClass.get();

            afterIsPresent.setStatus(toUpdate.getStatus());
            afterIsPresent.setTaxId(toUpdate.getTaxId());
            afterIsPresent.setNaturalId(toUpdate.getNaturalId());

            afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

            businessEntityRepository.save(afterIsPresent);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            throw new ResourceNotFoundException("Entity with id " + id + " not found");
    }

    @Transactional
    @RequestMapping(value = "/businessEntity/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ApiOperation(value = "Deletes given entity")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

        Optional<BusinessEntityModel> softDelete = businessEntityRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal());
        if (softDelete.isPresent()) {
            BusinessEntityModel afterIsPresent = softDelete.get();
            afterIsPresent.setStatus(Status.UNPUBLISHED.ordinal());
            afterIsPresent.setLastModifiedBy(lastModifiedBy);
            businessEntityRepository.save(afterIsPresent);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            throw new ResourceNotFoundException("Entity with id " + id + " not found");
    }

    // region private
    private HttpHeaders getHttpHeaders(@Valid @RequestBody BusinessEntityModel newObject) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObject.getId()).toUri());
        return responseHeaders;
    }

    private void checkIfExist(Long id) throws ResourceNotFoundException {
        businessEntityRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal()).orElseThrow(() -> new ResourceNotFoundException("Entity with id " + id + " not found"));
    }
    // endregion
}

