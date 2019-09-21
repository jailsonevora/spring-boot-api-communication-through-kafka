package com.BusinessEntityManagementSystem.dataAccessObject;

import com.BusinessEntityManagementSystem.models.CommuneModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ICommuneRepository extends PagingAndSortingRepository<CommuneModel, Long> {

    @Query(value="select BEMS_Commune.* from BEMS_Commune \n" +
            "inner join BEMS_County on BEMS_County.ID_County = BEMS_Commune.County\n" +
            "where BEMS_Commune.County = ?1 and BEMS_Commune.Status = 1 and BEMS_County.Status = 1 \n#pageable\n",
            nativeQuery=true)
    Page<CommuneModel> findCommuneModelByCounty(Long id, Pageable pageable);

    Optional<CommuneModel> findByIdAndStatus(long id, int status);

    Page<CommuneModel> findAllByStatus(int status, Pageable pageable);
}