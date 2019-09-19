package com.BusinessEntityManagementSystem.dataAccessObject;

import com.BusinessEntityManagementSystem.models.VillageModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IVillage extends PagingAndSortingRepository<VillageModel, Long> {

    Optional<VillageModel> findByIdAndStatus(long id, int status);

    Page<VillageModel> findAllByStatus(int status, Pageable pageable);
}
