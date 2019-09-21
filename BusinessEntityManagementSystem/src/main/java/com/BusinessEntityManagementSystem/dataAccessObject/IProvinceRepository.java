package com.BusinessEntityManagementSystem.dataAccessObject;

import com.BusinessEntityManagementSystem.models.ProvinceModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IProvinceRepository extends PagingAndSortingRepository<ProvinceModel, Long> {

    @Query(value="select BEMS_Province.* from BEMS_Province\n" +
            "inner join BEMS_Country on BEMS_Country.ID_Country = BEMS_Province.Country\n" +
            "where BEMS_Province.Country = ?1 and BEMS_Province.Status = 1 and BEMS_Country.Status = 1 \n#pageable\n",
            nativeQuery=true)
    Page<ProvinceModel> findProvinceModelByCountry(Long id, Pageable pageable);

    Optional<ProvinceModel> findByIdAndStatus(long id, int status);

    Page<ProvinceModel> findAllByStatus(int status, Pageable pageable);
}
