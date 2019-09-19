package com.BusinessEntityManagementSystem.dataAccessObject;

import com.BusinessEntityManagementSystem.models.BusinessEntityModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IBusinessEntityRepository extends PagingAndSortingRepository<BusinessEntityModel, Long> {

    Optional<BusinessEntityModel> findByIdAndStatus(long id, int status);

    Page<BusinessEntityModel> findAllByStatus(int status, Pageable pageable);

    boolean existsBusinessEntityModelByNaturalId(String key);
}
