package com.BusinessStatisticsUnitFiles.v1.controllers;

import com.BusinessStatisticsUnitFiles.dataAccessObject.IAddressRepository;
import com.BusinessStatisticsUnitFiles.interfaces.resource.IGenericCRUD;
import com.BusinessStatisticsUnitFiles.models.AddressModel;
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

@RestController("AddressV1")
@RequestMapping("/api/businessStatisticsUnitFiles/v1")
@Api(value = "address")
public class AddressController implements IGenericCRUD<AddressModel> {

    @PersistenceContext
    private EntityManager entityManager;
    private final IAddressRepository addressRepository;

    @Autowired
    public AddressController(IAddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }


    @RequestMapping(value = "/address/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves given address", response= AddressModel.class)
    public ResponseEntity<?> show(@Valid @PathVariable Long id){
        CheckExistence(id);
        return new ResponseEntity<> (addressRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal()), HttpStatus.OK);
    }

    @RequestMapping(value="/address", method=RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves all the addresses", response= AddressModel.class, responseContainer="List")
    public ResponseEntity<Page<AddressModel>> showall(Pageable pageable) {
        return new ResponseEntity<>(addressRepository.findAllByStatus(Status.PUBLISHED.ordinal(), pageable), HttpStatus.OK);
    }


    @Transactional
    @RequestMapping(value = "/address", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Creates a new address", notes="The newly created address Id will be sent in the location response header")
    public ResponseEntity<Void> create(@Valid @RequestBody AddressModel newAddress){

        newAddress = addressRepository.save(newAddress);

        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newAddress.getAddressId()).toUri());

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

    }

    @Transactional
    @RequestMapping(value = "/address/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ApiOperation(value = "Updates given address")
    public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody AddressModel toUpdate){

        Optional<AddressModel> updatedOptionalClass = addressRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal());
        if (updatedOptionalClass.isPresent()){

            AddressModel afterIsPresent = updatedOptionalClass.get();
            afterIsPresent.setStatus(toUpdate.getStatus());
            afterIsPresent.setEmail(toUpdate.getEmail());
            afterIsPresent.setAddress(toUpdate.getAddress());

            afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

            addressRepository.save(afterIsPresent);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            throw new ResourceNotFoundException("Address with id " + id + " not found");
    }

    @Transactional
    @RequestMapping(value = "/address/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ApiOperation(value = "Deletes given address")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

        Optional<AddressModel> softDelete = addressRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal());
        if (softDelete.isPresent()) {
            AddressModel afterIsPresent = softDelete.get();
            afterIsPresent.setStatus(Status.UNPUBLISHED.ordinal());
            afterIsPresent.setLastModifiedBy(lastModifiedBy);

            addressRepository.save(afterIsPresent);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            throw new ResourceNotFoundException("Address with id " + id + " not found");

    }

    // region private
    private void CheckExistence(Long id) throws ResourceNotFoundException {
        addressRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal()).orElseThrow(() -> new ResourceNotFoundException("Address with id " + id + " not found"));
    }
    // endregion


}

