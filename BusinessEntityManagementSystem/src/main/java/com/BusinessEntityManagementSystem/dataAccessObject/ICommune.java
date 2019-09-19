package com.BusinessEntityManagementSystem.dataAccessObject;

import com.BusinessEntityManagementSystem.models.CommuneModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ICommune extends PagingAndSortingRepository<CommuneModel, Long> {

    Optional<CommuneModel> findByIdAndStatus(long id, int status);

    Page<CommuneModel> findAllByStatus(int status, Pageable pageable);
}