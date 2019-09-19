package com.BusinessEntityManagementSystem.interfaces.resource;

import com.BusinessEntityManagementSystem.models.AffiliatedCompanyModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IAffiliatedCompany {

    ResponseEntity<?> show(@Valid @PathVariable Long id);

    ResponseEntity<Page<AffiliatedCompanyModel>> showall(Pageable pageable);

    ResponseEntity<Void> create(@Valid @RequestBody AffiliatedCompanyModel newAffiliatedCompany);

    ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody AffiliatedCompanyModel affiliatedCompanyToUpdate);

    ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);
}
