package com.BusinessEntityManagementSystem.dataAccessObject;

import com.BusinessEntityManagementSystem.models.CountyModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ICounty extends PagingAndSortingRepository<CountyModel, Long> {

    Optional<CountyModel> findByIdAndStatus(long id, int status);

    Page<CountyModel> findAllByStatus(int status, Pageable pageable);
}
