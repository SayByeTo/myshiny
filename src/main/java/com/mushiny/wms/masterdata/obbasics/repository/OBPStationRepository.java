package com.mushiny.wms.masterdata.obbasics.repository;

import com.mushiny.wms.common.respository.BaseRepository;
import com.mushiny.wms.masterdata.obbasics.domain.OBPStation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OBPStationRepository extends BaseRepository<OBPStation, String> {

    @Query("select p from OBPStation p where p.warehouseId = :warehouse and p.name = :name")
    OBPStation getByName(@Param("warehouse") String warehouse, @Param("name") String name);

//    @Query("select p from PackingStation p where p.packingStationType.id = :typeId")
//    List<PackingStation> getByTypeId(@Param("typeId") String typeId);
}
