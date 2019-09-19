package com.BusinessStatisticsUnitFiles.dataAccessObject;

import com.BusinessStatisticsUnitFiles.models.EconomicActivityCodeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IEconomicActivityCodeRepository extends PagingAndSortingRepository<EconomicActivityCodeModel, Long> {

    Optional<EconomicActivityCodeModel> findByIdAndStatus(long id, int status);

    Page<EconomicActivityCodeModel> findAllByStatus(int status, Pageable pageable);
}
