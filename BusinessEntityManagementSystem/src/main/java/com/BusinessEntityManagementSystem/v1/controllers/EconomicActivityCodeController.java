package com.BusinessEntityManagementSystem.v1.controllers;

import com.BusinessEntityManagementSystem.dataAccessObject.IEconomicActivityCodeRepository;
import com.BusinessEntityManagementSystem.interfaces.resource.IGenericCRUD;
import com.BusinessEntityManagementSystem.models.EconomicActivityCodeModel;
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

@RestController("EconomicActivityCodeV1")
@RequestMapping("/api/businessEntityManagementSystem/v1")
@Api(value = "economicActivityCode")
public class EconomicActivityCodeController implements IGenericCRUD<EconomicActivityCodeModel> {

    @PersistenceContext
    private EntityManager entityManager;
    private final IEconomicActivityCodeRepository economicActivityCodeRepository;

    @Autowired
    public EconomicActivityCodeController(IEconomicActivityCodeRepository caeRepository) {
        this.economicActivityCodeRepository = caeRepository;
    }

    @RequestMapping(value = "/economicActivityCode/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves given Economic Activity Code", response= EconomicActivityCodeModel.class)
    public ResponseEntity<?> get(@Valid @PathVariable Long id){
        checkIfExist(id);
        return new ResponseEntity<> (economicActivityCodeRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal()), HttpStatus.OK);
    }

    @RequestMapping(value="/economicActivityCode", method=RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves all the Economic Activity Code", response=EconomicActivityCodeModel.class, responseContainer="List")
    public ResponseEntity<Page<EconomicActivityCodeModel>> getAll(Pageable pageable) {
        return new ResponseEntity<>(economicActivityCodeRepository.findAllByStatus(Status.PUBLISHED.ordinal(), pageable), HttpStatus.OK);
    }


    @Transactional
    @RequestMapping(value = "/economicActivityCode", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Creates a new Economic Activity Code", notes="The newly created Economic Activity Code Id will be sent in the location response header")
    public ResponseEntity<String> create(@Valid @RequestBody EconomicActivityCodeModel newActivity){

        newActivity = economicActivityCodeRepository.save(newActivity);

        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newActivity.getId()).toUri());

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

    }


    @Transactional
    @RequestMapping(value = "/economicActivityCode/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ApiOperation(value = "Updates given Economic Activity Code")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody EconomicActivityCodeModel toUpdate){

        Optional<EconomicActivityCodeModel> updatedOptionalClass = economicActivityCodeRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal());

        if (updatedOptionalClass.isPresent()){

            EconomicActivityCodeModel afterIsPresent = updatedOptionalClass.get();

            afterIsPresent.setEconomicActivityCode(toUpdate.getEconomicActivityCode());
            afterIsPresent.setDescription(toUpdate.getDescription());
            afterIsPresent.setStatus(toUpdate.getStatus());
            afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

            economicActivityCodeRepository.save(afterIsPresent);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            throw new ResourceNotFoundException("Entity with id " + id + " not found");
    }

    @Transactional
    @RequestMapping(value = "/economicActivityCode/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ApiOperation(value = "Delete given Economic Activity Code")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){
        //CheckExistence(id);
        //economicActivityCodeRepository.deleteById(id);

        Optional<EconomicActivityCodeModel> softDelete = economicActivityCodeRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal());
        if (softDelete.isPresent()) {
            EconomicActivityCodeModel afterIsPresent = softDelete.get();
            afterIsPresent.setStatus(Status.UNPUBLISHED.ordinal());
            afterIsPresent.setLastModifiedBy(lastModifiedBy);

            economicActivityCodeRepository.save(afterIsPresent);
        }
        else
            throw new ResourceNotFoundException("Economic Activity Code with id " + id + " not found");
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // region private
    private void checkIfExist(Long id) throws ResourceNotFoundException {
        economicActivityCodeRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal()).orElseThrow(() -> new ResourceNotFoundException("Economic Activity Code with id " + id + " not found"));
    }
    // endregion




}
