package com.BusinessEntityManagementSystem.dataAccessObject;

import com.BusinessEntityManagementSystem.models.AffiliatedCompanyModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IAffiliatedCompanyRepository extends PagingAndSortingRepository<AffiliatedCompanyModel, Long> {

    Optional<AffiliatedCompanyModel> findByIdAndStatus(long id, int status);

    Page<AffiliatedCompanyModel> findAllByStatus(int status, Pageable pageable);
}
