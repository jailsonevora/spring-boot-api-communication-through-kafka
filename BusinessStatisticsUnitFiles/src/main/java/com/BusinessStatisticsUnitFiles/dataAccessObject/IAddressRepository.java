package com.BusinessStatisticsUnitFiles.dataAccessObject;

import com.BusinessStatisticsUnitFiles.models.AddressModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IAddressRepository extends PagingAndSortingRepository<AddressModel, Long> {

    Optional<AddressModel> findByIdAndStatus(long id, int status);

    Page<AddressModel> findAllByStatus(int status, Pageable pageable);
}
