package com.BusinessEntityManagementSystem.v1.controllers;

import com.BusinessEntityManagementSystem.dataAccessObject.ICountryRepository;
import com.BusinessEntityManagementSystem.dataAccessObject.ICountyRepository;
import com.BusinessEntityManagementSystem.models.CountryModel;
import com.Common.Util.Status;
import com.Common.exception.ResourceNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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

@RestController("CountryControllerV1")
@RequestMapping("/api/businessEntityManagementSystem/v1")
@Api(value = "countries")
public class CountryController {

    @PersistenceContext
    private EntityManager entityManager;
    private final ICountryRepository countryRepository;

    @Autowired
    public CountryController(ICountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @RequestMapping(value = "/countries/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves given country", response= CountryModel.class)
    public ResponseEntity<?> get(@Valid @PathVariable Long id){
        verify(id);
        return new ResponseEntity<> (countryRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal()), HttpStatus.OK);
    }

    @RequestMapping(value="/countries", method=RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves all the countries", response=CountryModel.class, responseContainer="List")
    public ResponseEntity<Page<CountryModel>> getAll(Pageable pageable) {
        return new ResponseEntity<>(countryRepository.findAllByStatus(Status.PUBLISHED.ordinal(), pageable), HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value = "/countries", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Creates a new country", notes="The newly created country Id will be sent in the location response header")
    public ResponseEntity<Void> create(@Valid @RequestBody CountryModel newCountry){

        newCountry = countryRepository.save(newCountry);

        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newCountry.getId()).toUri());

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

    }

    @Transactional
    @RequestMapping(value = "/countries/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ApiOperation(value = "Updates given country")
    public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody CountryModel toUpdate){

        Optional<CountryModel> countryUpdatedOptional = countryRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal());
        if (countryUpdatedOptional.isPresent()){

            CountryModel country = countryUpdatedOptional.get();
            country.setStatus(toUpdate.getStatus());
            country.setCountry(toUpdate.getCountry());
            country.setLastModifiedBy(toUpdate.getLastModifiedBy());

            countryRepository.save(country);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            throw new ResourceNotFoundException("Country with id " + id + " not found");
    }

    @Transactional
    @RequestMapping(value = "/countries/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ApiOperation(value = "Deletes given country")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

        Optional<CountryModel> softDelete = countryRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal());
        if (softDelete.isPresent()) {
            CountryModel afterIsPresent = softDelete.get();
            afterIsPresent.setStatus(Status.UNPUBLISHED.ordinal());
            afterIsPresent.setLastModifiedBy(lastModifiedBy);

            countryRepository.save(afterIsPresent);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            throw new ResourceNotFoundException("Country with id " + id + " not found");
    }

    // region private
    private void verify(Long id) throws ResourceNotFoundException {
        countryRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal()).orElseThrow(() -> new ResourceNotFoundException("Country with id " + id + " not found"));
    }
	/*private void verify(Long id) throws ResourceNotFoundException {
		Optional<Pais> pais = paisRepository.findById(id);
		if(!pais.isPresent()) {
			throw new ResourceNotFoundException("Country with id " + id + " not found");
		}
	}*/
    // endregion

}

