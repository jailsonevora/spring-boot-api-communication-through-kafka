package com.BusinessEntityManagementSystem.v1.controllers;

import com.BusinessEntityManagementSystem.dataAccessObject.IPartnerRepository;
import com.BusinessEntityManagementSystem.interfaces.resource.IGenericCRUD;
import com.BusinessEntityManagementSystem.models.PartnerModel;
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

@RestController("PartnerV1")
@RequestMapping("/api/businessStatisticsUnitFiles/v1")
@Api(value = "partners")
public class PartnerController implements IGenericCRUD<PartnerModel> {

    @PersistenceContext
    private EntityManager entityManager;
    private final IPartnerRepository partnerRepository;

    @Autowired
    public PartnerController(IPartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }


    @RequestMapping(value = "/partners/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves given partner", response= PartnerModel.class)
    public ResponseEntity<?> show(@Valid @PathVariable Long id){
        CheckExistence(id);
        return new ResponseEntity<> (partnerRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal()), HttpStatus.OK);
    }

    @RequestMapping(value="/partners", method=RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves all the partners", response=PartnerModel.class, responseContainer="List")
    public ResponseEntity<Page<PartnerModel>> showall(Pageable pageable) {
        return new ResponseEntity<>(partnerRepository.findAllByStatus(Status.PUBLISHED.ordinal(), pageable), HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value = "/partners", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Creates a new partner", notes="The newly created partner Id will be sent in the location response header")
    public ResponseEntity<Void> create(@Valid @RequestBody PartnerModel newPartner){

        newPartner = partnerRepository.save(newPartner);

        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newPartner.getId()).toUri());

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

    }

    @Transactional
    @RequestMapping(value = "/partners/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ApiOperation(value = "Updates given partner")
    public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody PartnerModel toUpdate){

        Optional<PartnerModel> updatedOptionalClass = partnerRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal());
        if (updatedOptionalClass.isPresent()){

            PartnerModel afterIsPresent = updatedOptionalClass.get();
            afterIsPresent.setStatus(toUpdate.getStatus());
            afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());
            afterIsPresent.setName(toUpdate.getName());
            afterIsPresent.setNum(toUpdate.getNum());

            partnerRepository.save(afterIsPresent);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            throw new ResourceNotFoundException("Partner with id " + id + " not found");
    }

    @Transactional
    @RequestMapping(value = "/partners/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ApiOperation(value = "Deletes given partner")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

        Optional<PartnerModel> softDelete = partnerRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal());
        if (softDelete.isPresent()) {
            PartnerModel afterIsPresent = softDelete.get();
            afterIsPresent.setStatus(Status.UNPUBLISHED.ordinal());
            afterIsPresent.setLastModifiedBy(lastModifiedBy);

            partnerRepository.save(afterIsPresent);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            throw new ResourceNotFoundException("Entity with id " + id + " not found");
    }

    // region private
    private void CheckExistence(Long id) throws ResourceNotFoundException {
        partnerRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal()).orElseThrow(() -> new ResourceNotFoundException("Partner with id " + id + " not found"));
    }
    // endregion



}

