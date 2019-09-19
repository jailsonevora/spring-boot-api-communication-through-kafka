package com.BusinessEntityManagementSystem.interfaces.resource;

import com.BusinessEntityManagementSystem.models.StoreModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IStore {

    ResponseEntity<?> show(@Valid @PathVariable Long id);

    ResponseEntity<Page<StoreModel>> showall(Pageable pageable);

    ResponseEntity<Void> create(@Valid @RequestBody StoreModel newStore);

    ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody StoreModel storeToUpdate);

    ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);
}
