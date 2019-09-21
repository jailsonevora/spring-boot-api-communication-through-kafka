package com.BusinessEntityManagementSystem.interfaces.resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IGenericCRUD<T> {

    ResponseEntity<?> get(@Valid @PathVariable Long id);

    ResponseEntity<Page<T>> getAll(Pageable pageable);

    ResponseEntity<Void> create(@Valid @RequestBody T newEntry);

    ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody T toUpdate);

    ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);
}
