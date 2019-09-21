package com.BusinessEntityManagementSystem.v1.controllers;

import com.BusinessEntityManagementSystem.dataAccessObject.ICommuneRepository;
import com.BusinessEntityManagementSystem.dataAccessObject.ICountyRepository;
import com.BusinessEntityManagementSystem.models.CommuneModel;
import com.BusinessEntityManagementSystem.models.CountyModel;
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

@RestController("CommuneV1")
@RequestMapping("/api/businessEntityManagementSystem/v1")
@Api(value = "communes")
public class CommuneController {

    @PersistenceContext
    private EntityManager entityManager;
    private final ICommuneRepository communeRepository;
    private final ICountyRepository countyRepository;

    @Autowired
    public CommuneController(ICommuneRepository communeRepository, ICountyRepository countyRepository) {
        this.communeRepository = communeRepository;
        this.countyRepository = countyRepository;
    }

    @RequestMapping(value = "/communes/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves given commune", response= CommuneModel.class)
    public ResponseEntity<?> get(@Valid @PathVariable Long id){
        checkIfExist(id);
        return new ResponseEntity<> (communeRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal()), HttpStatus.OK);
    }

    @RequestMapping(value="/communes", method=RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves all the communes", response=CommuneModel.class, responseContainer="List")
    public ResponseEntity<Page<CommuneModel>> getAll(@Valid Pageable pageable) {
        return new ResponseEntity<>(communeRepository.findAllByStatus(Status.PUBLISHED.ordinal(), pageable), HttpStatus.OK);
    }

    @RequestMapping(value="/counties/{countyId}/communes", method=RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves all the communes by county", response=CommuneModel.class, responseContainer="List")
    public ResponseEntity<Page<CommuneModel>> getAllByProvince(@Valid @PathVariable Long countyId, @Valid Pageable pageable) {
        return new ResponseEntity<>(communeRepository.findCommuneModelByCounty(countyId, pageable), HttpStatus.OK);
    }


    @Transactional
    @RequestMapping(value = "/counties/{countyId}/communes", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Creates a new commune", notes="The newly created commune Id will be sent in the location response header")
    public ResponseEntity<Void> create(@Valid @PathVariable Long countyId, @Valid @RequestBody CommuneModel newCommune){

        Optional<CountyModel> updatedOptionalClass = countyRepository.findByIdAndStatus(countyId, Status.PUBLISHED.ordinal());

        if (updatedOptionalClass.isPresent()) {
            newCommune.setCounty(updatedOptionalClass.get());
            // Set the location header for the newly created resource
            return new ResponseEntity <>(null, getHttpHeaders(communeRepository.save(newCommune)), HttpStatus.CREATED);
        }
        else
            throw new ResourceNotFoundException("County with id " + countyId + " not found");

    }

    @Transactional
    @RequestMapping(value = "/counties/{countyId}/communes/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ApiOperation(value = "Updates given commune")
    public ResponseEntity<Void> update(@Valid @PathVariable Long countyId, @Valid @PathVariable Long id, @Valid @RequestBody CommuneModel toUpdate){

        if(!countyRepository.existsById(countyId)) {
            throw new ResourceNotFoundException("County " + countyId + " not found");
        }

        Optional<CommuneModel> updatedOptionalClass = communeRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal());
        if (updatedOptionalClass.isPresent()){

            CommuneModel afterIsPresent = updatedOptionalClass.get();
            afterIsPresent.setStatus(toUpdate.getStatus());
            afterIsPresent.setCounty(toUpdate.getCounty());
            afterIsPresent.setDescription(toUpdate.getDescription());
            afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

            communeRepository.save(afterIsPresent);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            throw new ResourceNotFoundException("Commune with id " + id + " not found");
    }

    @Transactional
    @RequestMapping(value = "/counties/{countyId}/communes/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ApiOperation(value = "Deletes given commune")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long countyId, @Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

        Optional<CommuneModel> softDelete = communeRepository.findByIdAndStatus(id,Status.PUBLISHED.ordinal());
        if (softDelete.isPresent()) {
            CommuneModel afterIsPresent = softDelete.get();
            afterIsPresent.setStatus(Status.UNPUBLISHED.ordinal());
            afterIsPresent.setLastModifiedBy(lastModifiedBy);

            communeRepository.save(afterIsPresent);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            throw new ResourceNotFoundException("Commune with id " + id + " not found");

    }

    // region private
    private void checkIfExist(Long id) throws ResourceNotFoundException {
        communeRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal()).orElseThrow(() -> new ResourceNotFoundException("Commune with id " + id + " not found"));
    }

    private HttpHeaders getHttpHeaders(@Valid @RequestBody CommuneModel newObject) {
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObject.getId()).toUri());
        return responseHeaders;
    }

    // endregion

}
