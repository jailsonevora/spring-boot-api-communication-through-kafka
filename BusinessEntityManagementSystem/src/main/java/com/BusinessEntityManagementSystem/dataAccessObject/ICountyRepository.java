package com.BusinessEntityManagementSystem.dataAccessObject;

import com.BusinessEntityManagementSystem.models.CountyModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ICountyRepository extends PagingAndSortingRepository<CountyModel, Long> {

    @Query(value="select BEMS_County.* from BEMS_County\n" +
            "inner join BEMS_Province on BEMS_Province.ID_Province = BEMS_municipio.Province\n" +
            "where BEMS_municipio.Province = ?1 and BEMS_County.Status = 1 and BEMS_County.Status = 1 \n#pageable\n",
            nativeQuery=true)
    Page<CountyModel> findCountyModelByProvince(Long id, Pageable pageable);

    Optional<CountyModel> findByIdAndStatus(long id, int status);

    Page<CountyModel> findAllByStatus(int status, Pageable pageable);
}
