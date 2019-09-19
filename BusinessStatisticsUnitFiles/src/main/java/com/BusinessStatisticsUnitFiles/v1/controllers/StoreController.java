package com.BusinessStatisticsUnitFiles.v1.controllers;

import com.BusinessStatisticsUnitFiles.dataAccessObject.IStoreRepository;
import com.BusinessStatisticsUnitFiles.interfaces.resource.IGenericCRUD;
import com.BusinessStatisticsUnitFiles.models.StoreModel;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Optional;

@RestController("Store")
@RequestMapping("/api/businessStatisticsUnitFiles/v1")
@Api(value = "store")
public class StoreController implements IGenericCRUD<StoreModel> {

    @PersistenceContext
    private EntityManager entityManager;
    private final IStoreRepository storeRepository;

    @Autowired
    public StoreController(IStoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @RequestMapping(value = "/store/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves given store", response= StoreModel.class)
    public ResponseEntity<?> show(@Valid @PathVariable Long id){
        CheckExistence(id);
        return new ResponseEntity<> (storeRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal()), HttpStatus.OK);
    }

    @RequestMapping(value="/store", method=RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves all the stores", response=StoreModel.class, responseContainer="List")
    public ResponseEntity<Page<StoreModel>> showall(Pageable pageable) {
        return new ResponseEntity<>(storeRepository.findAllByStatus(Status.PUBLISHED.ordinal(), pageable), HttpStatus.OK);
    }


    @Transactional
    @RequestMapping(value = "/store", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Creates a new store", notes="The newly created store ID will be sent in the location response header")
    public ResponseEntity<Void> create(@Valid @RequestBody StoreModel newStore){

        newStore = storeRepository.save(newStore);

        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newStore.getStoreId()).toUri());

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

    }

    @Transactional
    @RequestMapping(value = "/store/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ApiOperation(value = "Updates given store")
    public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody StoreModel toUpdate){

        Optional<StoreModel> updatedOptionalClass = storeRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal());

        if (updatedOptionalClass.isPresent()){

            StoreModel afterIsPresent = updatedOptionalClass.get();

            afterIsPresent.setStatus(toUpdate.getStatus());
            afterIsPresent.setName(toUpdate.getName());
            afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

            storeRepository.save(afterIsPresent);

            return new ResponseEntity<>(HttpStatus.OK);
        }else{
            throw new ResourceNotFoundException("Store with id " + id + " not found");

        }//endif
    }

    @Transactional
    @RequestMapping(value = "/store/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ApiOperation(value = "Deletes given store")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

        Optional<StoreModel> softDelete = storeRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal());
        if (softDelete.isPresent()) {
            StoreModel afterIsPresent = softDelete.get();
            afterIsPresent.setStatus(Status.UNPUBLISHED.ordinal());
            afterIsPresent.setLastModifiedBy(lastModifiedBy);

            storeRepository.save(afterIsPresent);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            throw new ResourceNotFoundException("Store with id " + id + " not found");

    }

    // region private
    private void CheckExistence(Long id) throws ResourceNotFoundException {
        storeRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal()).orElseThrow(() -> new ResourceNotFoundException("Store with id " + id + " not found"));
    }
    // endregion
}
