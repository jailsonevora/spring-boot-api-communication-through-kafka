package com.BusinessStatisticsUnitFiles.dataAccessObject;

import com.BusinessStatisticsUnitFiles.models.PartnerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IPartnerRepository extends PagingAndSortingRepository<PartnerModel, Long> {

    Optional<PartnerModel> findByIdAndStatus(long id, int status);

    Page<PartnerModel> findAllByStatus(int status, Pageable pageable);
}
