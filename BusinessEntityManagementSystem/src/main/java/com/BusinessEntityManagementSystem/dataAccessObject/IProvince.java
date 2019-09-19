package com.BusinessEntityManagementSystem.dataAccessObject;

import com.BusinessEntityManagementSystem.models.ProvinceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IProvince extends PagingAndSortingRepository<ProvinceModel, Long> {

    Optional<ProvinceModel> findByIdAndStatus(long id, int status);

    Page<ProvinceModel> findAllByStatus(int status, Pageable pageable);
}
