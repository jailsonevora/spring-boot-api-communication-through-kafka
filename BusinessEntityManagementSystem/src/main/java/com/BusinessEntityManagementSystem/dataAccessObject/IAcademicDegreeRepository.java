package com.BusinessEntityManagementSystem.dataAccessObject;

import com.BusinessEntityManagementSystem.models.AcademicDegreeModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IAcademicDegreeRepository extends PagingAndSortingRepository<AcademicDegreeModel, Long> {

    Optional<AcademicDegreeModel> findByIdAndStatus(long id, int status);

    Page<AcademicDegreeModel> findAllByStatus(int status, Pageable pageable);
}
