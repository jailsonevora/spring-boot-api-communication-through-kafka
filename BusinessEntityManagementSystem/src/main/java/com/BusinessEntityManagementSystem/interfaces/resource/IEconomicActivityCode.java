package com.BusinessEntityManagementSystem.interfaces.resource;

import com.BusinessEntityManagementSystem.models.EconomicActivityCodeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IEconomicActivityCode {

    ResponseEntity<?> show(@Valid @PathVariable Long id);

    ResponseEntity<Page<EconomicActivityCodeModel>> showall(Pageable pageable);

    ResponseEntity<Void> create(@Valid @RequestBody EconomicActivityCodeModel newEconomicActivityCode);

    ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody EconomicActivityCodeModel economicActivityCodeToUpdate);

    ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);
}
