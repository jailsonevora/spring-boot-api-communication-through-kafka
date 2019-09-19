package com.BusinessStatisticsUnitFiles.dataAccessObject;

import com.BusinessStatisticsUnitFiles.models.StoreModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IStoreRepository extends PagingAndSortingRepository<StoreModel, Long> {

    Optional<StoreModel> findByIdAndStatus(long id, int status);

    Page<StoreModel> findAllByStatus(int status, Pageable pageable);
}
