package com.BusinessEntityManagementSystem.dataAccessObject;

import com.BusinessEntityManagementSystem.models.SectorModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ISector extends PagingAndSortingRepository<SectorModel, Long> {

    Optional<SectorModel> findByIdAndStatus(long id, int status);

    Page<SectorModel> findAllByStatus(int status, Pageable pageable);
}
