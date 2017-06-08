package com.mushiny.wms.masterdata.obbasics.repository;

import com.mushiny.wms.common.respository.BaseRepository;
import com.mushiny.wms.masterdata.obbasics.domain.RebatchStation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RebatchStationRepository extends BaseRepository<RebatchStation, String> {

    @Query("select p from RebatchStation p where p.warehouseId = :warehouse and p.name = :name")
    RebatchStation getByName(@Param("warehouse") String warehouse, @Param("name") String name);

}
