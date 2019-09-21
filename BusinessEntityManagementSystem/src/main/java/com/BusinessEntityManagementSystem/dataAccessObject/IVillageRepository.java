package com.BusinessEntityManagementSystem.dataAccessObject;

import com.BusinessEntityManagementSystem.models.VillageModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface IVillageRepository extends PagingAndSortingRepository<VillageModel, Long> {

    @Query(value="select BEMS_Village.* from BEMS_Village \n" +
            "inner join BEMS_Commune on BEMS_Commune.ID_Commune = BEMS_Village.Commune\n" +
            "where BEMS_Village.Commune = ?1 and BEMS_Village.Status = 1 and BEMS_Commune.Status = 1 \n#pageable\n",
            nativeQuery=true)
    Page<VillageModel> findVillageModelByCommune(Long id, Pageable pageable);

    Optional<VillageModel> findByIdAndStatus(long id, int Status);

    Page<VillageModel> findAllByStatus(int Status, Pageable pageable);
}
