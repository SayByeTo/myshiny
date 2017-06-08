package com.mushiny.wms.masterdata.ibbasics.repository;

import com.mushiny.wms.common.respository.BaseRepository;
import com.mushiny.wms.masterdata.ibbasics.domain.IBPStation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IBPStationRepository extends BaseRepository<IBPStation, String> {

    @Query("select p from IBPStation p where p.warehouseId = :warehouse and p.name = :name")
    IBPStation getByName(@Param("warehouse") String warehouse, @Param("name") String name);

//    @Query("select p from PackingStation p where p.packingStationType.id = :typeId")
//    List<PackingStation> getByTypeId(@Param("typeId") String typeId);
}
