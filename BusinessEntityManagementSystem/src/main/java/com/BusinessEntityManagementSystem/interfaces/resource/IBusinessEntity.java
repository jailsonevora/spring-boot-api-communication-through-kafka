package com.BusinessEntityManagementSystem.interfaces.resource;

import com.BusinessEntityManagementSystem.models.BusinessEntityModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IBusinessEntity {

    ResponseEntity<?> show(@Valid @PathVariable Long id);

    ResponseEntity<Page<BusinessEntityModel>> showall(Pageable pageable);

    ResponseEntity<Void> create(@Valid @RequestBody BusinessEntityModel newBusinessEntity);

    ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody BusinessEntityModel businessEntityToUpdate);

    ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);
}
