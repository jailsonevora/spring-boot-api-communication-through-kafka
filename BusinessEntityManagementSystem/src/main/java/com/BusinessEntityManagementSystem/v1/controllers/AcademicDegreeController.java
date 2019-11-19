package com.BusinessEntityManagementSystem.v1.controllers;

import com.BusinessEntityManagementSystem.dataAccessObject.IAcademicDegreeRepository;
import com.BusinessEntityManagementSystem.interfaces.resource.IGenericCRUD;
import com.BusinessEntityManagementSystem.models.AcademicDegreeModel;
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

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.Optional;

@RestController("AcademicDegreeV1")
@RequestMapping("/api/businessEntityManagementSystem/v1")
@Api(value = "academicDegrees")
public class AcademicDegreeController implements IGenericCRUD<AcademicDegreeModel> {


    private final IAcademicDegreeRepository academicDegreeRepository;

    @Autowired
    public AcademicDegreeController(IAcademicDegreeRepository academicDegreeRepository) {
        this.academicDegreeRepository = academicDegreeRepository;
    }


    @RequestMapping(value = "/academicDegrees/{id}", method = RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves given degree", response= AcademicDegreeModel.class)
    public ResponseEntity<?> get(@Valid @PathVariable Long id){
        checkIfExist(id);
        return new ResponseEntity<> (academicDegreeRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal()), HttpStatus.OK);
    }

    @RequestMapping(value="/academicDegrees", method=RequestMethod.GET, produces = "application/json")
    @ApiOperation(value = "Retrieves all the degrees", response=AcademicDegreeModel.class, responseContainer="List")
    public ResponseEntity<Page<AcademicDegreeModel>> getAll(Pageable pageable) {
        return new ResponseEntity<>(academicDegreeRepository.findAllByStatus(Status.PUBLISHED.ordinal(), pageable), HttpStatus.OK);
    }

    @Transactional
    @RequestMapping(value = "/academicDegrees", method = RequestMethod.POST, produces = "application/json")
    @ApiOperation(value = "Creates a new degree", notes="The newly created degree Id will be sent in the location response header")
    public ResponseEntity<String> create(@Valid @RequestBody AcademicDegreeModel newDegree){

        newDegree = academicDegreeRepository.save(newDegree);

        // Set the location header for the newly created resource
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setLocation(ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newDegree.getId()).toUri());

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED);

    }

    @Transactional
    @RequestMapping(value = "/academicDegrees/{id}", method = RequestMethod.PUT, produces = "application/json")
    @ApiOperation(value = "Updates given degree")
    public ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody AcademicDegreeModel toUpdate){

        Optional<AcademicDegreeModel> updatedOptionalClass = academicDegreeRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal());
        if (updatedOptionalClass.isPresent()){

            AcademicDegreeModel afterIsPresent = updatedOptionalClass.get();
            afterIsPresent.setStatus(toUpdate.getStatus());
            afterIsPresent.setDegree(toUpdate.getDegree());
            afterIsPresent.setLastModifiedBy(toUpdate.getLastModifiedBy());

            academicDegreeRepository.save(afterIsPresent);

            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            throw new ResourceNotFoundException("Degree with id " + id + " not found");
    }

    @Transactional
    @RequestMapping(value = "/academicDegrees/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ApiOperation(value = "Deletes given degree")
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy){

        Optional<AcademicDegreeModel> softDelete = academicDegreeRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal());
        if (softDelete.isPresent()) {
            AcademicDegreeModel afterIsPresent = softDelete.get();
            afterIsPresent.setStatus(Status.UNPUBLISHED.ordinal());
            afterIsPresent.setLastModifiedBy(lastModifiedBy);

            academicDegreeRepository.save(afterIsPresent);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            throw new ResourceNotFoundException("Degree with id " + id + " not found");
    }

    // region private
    private void checkIfExist(Long id) throws ResourceNotFoundException {
        academicDegreeRepository.findByIdAndStatus(id, Status.PUBLISHED.ordinal()).orElseThrow(() -> new ResourceNotFoundException("Degree with id " + id + " not found"));
    }
    // endregion



}
