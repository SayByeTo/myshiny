package com.mushiny.wms.masterdata.ibbasics.repository;

import com.mushiny.wms.common.respository.BaseRepository;
import com.mushiny.wms.masterdata.ibbasics.domain.StockTakingStation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StockTakingStationRepository extends BaseRepository<StockTakingStation, String> {

    @Query("select p from StockTakingStation p where p.warehouseId = :warehouse and p.name = :name")
    StockTakingStation getByName(@Param("warehouse") String warehouse, @Param("name") String name);

//    @Query("select p from PackingStation p where p.packingStationType.id = :typeId")
//    List<PackingStation> getByTypeId(@Param("typeId") String typeId);
}
