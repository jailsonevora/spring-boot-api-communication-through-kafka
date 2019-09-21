package com.BusinessEntityManagementSystem.interfaces.resource;

import com.BusinessEntityManagementSystem.models.AddressModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IAddress {

    ResponseEntity<?> show(@Valid @PathVariable Long id);

    ResponseEntity<Page<AddressModel>> showall(Pageable pageable);

    ResponseEntity<Void> create(@Valid @RequestBody AddressModel newAddress);

    ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody AddressModel addressToUpdate);

    ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);
}
