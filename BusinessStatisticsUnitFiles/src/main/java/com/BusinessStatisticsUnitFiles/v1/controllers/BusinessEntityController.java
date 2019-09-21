package com.BusinessStatisticsUnitFiles.v1.controllers;

import com.BusinessStatisticsUnitFiles.dataAccessObject.IBusinessEntityRepository;
import com.BusinessStatisticsUnitFiles.interfaces.resource.IGenericCRUD;
import com.BusinessStatisticsUnitFiles.models.BusinessEntityModel;
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
import java.util.Optional;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

@RestController("BusinessEntityV1")
@RequestMapping("/api/businessStatisticsUnitFiles/v1")
@Api(value = "businessEntity")
public class BusinessEntityController implements IGenericCRUD<BusinessEntityModel> {

    @PersistenceContext
    private EntityManager entityManager;
    private final IBusinessEntityRepository businessEntityRepository;

    @Autowired
    public BusinessEntityController(IBusinessEntityRepository businessEntityRepository) {
        this.businessEntityRepository = businessEntityRepository;
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

    @RequestMapping(value="/checkentity/{key}", method=RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Validate entry entity")
    public ResponseEntity<?> checkEntity(@Valid @PathVariable String key) {

        if (businessEntityRepository.existsBusinessEntityModelByNaturalId(key))
            return new ResponseEntity<String>(HttpStatus.OK);
        else
            throw new ResourceNotFoundException("Entity with validation code " + key + " not found");
    }

    @Transactional
    @RequestMapping(value = "/businessEntity", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Creates a new entity", notes="The newly created entity Id will be sent in the location response header")
    public ResponseEntity<Void> create(@Valid @RequestBody BusinessEntityModel newEntity){

        // Set the location header for the newly created resource*/
        return new ResponseEntity <>(null, getHttpHeaders(businessEntityRepository.save(newEntity)), HttpStatus.CREATED);

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
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObject.getBusinessId()).toUri());
        return responseHeaders;
    }

    private void checkIfExist(Long id) throws ResourceNotFoundException {
        businessEntityRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal()).orElseThrow(() -> new ResourceNotFoundException("Country with id " + id + " not found"));
    }
    // endregion


    // region HATEAOS
    /*@RequestMapping(value="/polls", method=RequestMethod.GET)
    public ResponseEntity<Iterable<BusinessEntityModel>> getAllEntities() {
        Iterable<BusinessEntityModel> allEntities = businessEntityRepository.findAll();
        for(BusinessEntityModel ent : allEntities) {
            updatePollResourceWithLinks(ent);
        }
        return new ResponseEntity<>(allEntities, HttpStatus.OK);
    }

    @RequestMapping(value="/polls/{pollId}", method=RequestMethod.GET)
    public ResponseEntity<?> getPoll(@PathVariable Long entityId) {
        Optional<BusinessEntityModel> ent = businessEntityRepository.findById(entityId);
        if (ent.isPresent()) {
            updatePollResourceWithLinks(ent.get());
        }
        return new ResponseEntity<> (ent, HttpStatus.OK);
    }

    private void updatePollResourceWithLinks(BusinessEntityModel entity) {
        entity.add(linkTo(methodOn(BusinessEntityController.class).getAllEntities()).slash(entity.
                getBusinessId()).withSelfRel());
        entity.add(linkTo(methodOn(AffiliatedCompanyController.class).getA(entity.getBusinessId())).
                withRel("votes"));
        entity.add(linkTo(methodOn(ContactController.class).computeResult(entity.
                getBusinessId())).withRel("compute-result"));
    }*/
    // endregion
}

