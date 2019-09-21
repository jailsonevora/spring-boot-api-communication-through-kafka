package com.BusinessStatisticsUnitFiles.v1.controllers;

import com.Common.Util.Status;
import com.Common.exception.ResourceNotFoundException;
import com.BusinessStatisticsUnitFiles.dataAccessObject.IAffiliatedCompanyRepository;
import com.BusinessStatisticsUnitFiles.interfaces.resource.IGenericCRUD;
import com.BusinessStatisticsUnitFiles.models.AffiliatedCompanyModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.Optional;

@RestController("AffiliatedCompanyV1")
@RequestMapping("/api/businessStatisticsUnitFiles/v1")
@Api(value = "affiliatedCompany")
public class AffiliatedCompanyController implements IGenericCRUD<AffiliatedCompanyModel> {

    private final IAffiliatedCompanyRepository affiliatedCompanyRepository;

    @Autowired
    public AffiliatedCompanyController(IAffiliatedCompanyRepository affiliatedCompanyRepository) {
        this.affiliatedCompanyRepository = affiliatedCompanyRepository;
    }

    @RequestMapping(value = "/affiliatedCompany/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves given affiliated company", response= AffiliatedCompanyModel.class)
    public ResponseEntity<?> get(@Valid @PathVariable Long id){
        CheckExistence(id);
        return new ResponseEntity<> (affiliatedCompanyRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal()), HttpStatus.OK);
    }

    @RequestMapping(value="/affiliatedCompany", method=RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves all the affiliated company", response= AffiliatedCompanyModel.class, responseContainer="List")
    public ResponseEntity<Page<AffiliatedCompanyModel>> getAll(Pageable pageable) {
        return new ResponseEntity<>(affiliatedCompanyRepository.findAllByStatus(Status.PUBLISHED.ordinal(), pageable), HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value = "/affiliatedCompany", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Creates a new Affiliated Company", notes="The newly created Affiliated Company Id will be sent in the location response header")
    public ResponseEntity<Void> create(@Valid @RequestBody AffiliatedCompanyModel newAffiliatedCompany){

        newAffiliatedCompany = affiliatedCompanyRepository.save(newAffiliatedCompany);

        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newAffiliatedCompany.getAffiliatedCompanyId()).toUri());

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);
    }

    @Transactional
    @RequestMapping(value = "/affiliatedCompany/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ApiOperation(value = "Updates given AffiliatedCompany")
    public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody AffiliatedCompanyModel toUpdate){

        Optional<AffiliatedCompanyModel> updatedOptionalClass = affiliatedCompanyRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal());

        if (updatedOptionalClass.isPresent()){

            AffiliatedCompanyModel afterIsPresent = updatedOptionalClass.get();

            afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

            afterIsPresent.setIntegrationDate(toUpdate.getIntegrationDate());
            afterIsPresent.setStatus(toUpdate.getStatus());
            afterIsPresent.setPercentageShared(toUpdate.getPercentageShared());

            affiliatedCompanyRepository.save(afterIsPresent);

            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            throw new ResourceNotFoundException("AffiliatedCompany with id " + id + " not found");

        }
    }

    @Transactional
    @RequestMapping(value = "/affiliatedCompany/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ApiOperation(value = "Deletes given AffiliatedCompany")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

        Optional<AffiliatedCompanyModel> softDelete = affiliatedCompanyRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal());
        if (softDelete.isPresent()) {
            AffiliatedCompanyModel afterIsPresent = softDelete.get();
            afterIsPresent.setStatus(Status.UNPUBLISHED.ordinal());
            afterIsPresent.setLastModifiedBy(lastModifiedBy);

            affiliatedCompanyRepository.save(afterIsPresent);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            throw new ResourceNotFoundException("Entity with id " + id + " not found");
    }

    // region private
    private void CheckExistence(Long id) throws ResourceNotFoundException {
        affiliatedCompanyRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal()).orElseThrow(() -> new ResourceNotFoundException("Affiliated company with id " + id + " not found"));
    }
    // endregion

}
