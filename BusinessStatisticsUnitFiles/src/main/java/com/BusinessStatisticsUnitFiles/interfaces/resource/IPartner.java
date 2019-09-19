package com.BusinessStatisticsUnitFiles.interfaces.resource;

import com.BusinessStatisticsUnitFiles.models.PartnerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface IPartner {

    ResponseEntity<?> show(@Valid @PathVariable Long id);

    ResponseEntity<Page<PartnerModel>> showall(Pageable pageable);

    ResponseEntity<Void> create(@Valid @RequestBody PartnerModel newPartner);

    ResponseEntity<Void> update(@Valid @PathVariable Long id, @Valid @RequestBody PartnerModel partnerToUpdate);

    ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody String lastModifiedBy);
}
