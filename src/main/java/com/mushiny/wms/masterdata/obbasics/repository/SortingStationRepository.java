package com.mushiny.wms.masterdata.obbasics.repository;

import com.mushiny.wms.common.respository.BaseRepository;
import com.mushiny.wms.masterdata.obbasics.domain.SortingStation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SortingStationRepository extends BaseRepository<SortingStation, String> {

    @Query("select p from SortingStation p where p.warehouseId = :warehouse and p.name = :name")
    SortingStation getByName(@Param("warehouse") String warehouse, @Param("name") String name);

}
