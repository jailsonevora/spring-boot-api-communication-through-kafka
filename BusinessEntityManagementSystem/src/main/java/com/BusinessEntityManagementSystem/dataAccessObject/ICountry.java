package com.BusinessEntityManagementSystem.dataAccessObject;

import com.BusinessEntityManagementSystem.models.CountryModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ICountry extends PagingAndSortingRepository<CountryModel, Long> {

    Optional<CountryModel> findByIdAndStatus(long id, int status);

    Page<CountryModel> findAllByStatus(int status, Pageable pageable);
}
