package com.BusinessStatisticsUnitFiles.dataAccessObject;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;

import java.io.Serializable;

@NoRepositoryBean
public interface ICrudRepository<T, ID extends Serializable> extends PagingAndSortingRepository<T, ID> {
    // Create and Update Methods
    <S extends T> S save(S entity);
    <S extends T> Iterable<S> save(Iterable<S> entities);

    // Finder Methods
    T findOne(ID id);
    Iterable<T> findAll();
    Iterable<T> findAll(Iterable<ID> ids);

    // Delete Methods
    void delete(ID id);
    void delete(T entity);
    void delete(Iterable<? extends T> entities);
    void deleteAll();

    // Utility Methods
    long count();
    boolean exists(ID id);
}