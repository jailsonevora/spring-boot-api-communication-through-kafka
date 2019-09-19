package com.BusinessEntityManagementSystem.dataAccessObject;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

@NoRepositoryBean
public interface IGenericRepository<T> extends PagingAndSortingRepository<T, Long> {

    Optional<T> findByIdAndStatus(long id, int status);

    Page<T> findAllByStatus(int status, Pageable pageable);
}
