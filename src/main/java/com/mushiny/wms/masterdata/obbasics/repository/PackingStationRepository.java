package com.mushiny.wms.masterdata.obbasics.repository;

import com.mushiny.wms.common.respository.BaseRepository;
import com.mushiny.wms.masterdata.obbasics.domain.PackingStation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PackingStationRepository extends BaseRepository<PackingStation, String> {

    @Query("select p from PackingStation p where p.warehouseId = :warehouse and p.name = :name")
    PackingStation getByName(@Param("warehouse") String warehouse, @Param("name") String name);

    @Query("select p from PackingStation p where p.packingStationType.id = :typeId")
    List<PackingStation> getByTypeId(@Param("typeId") String typeId);
}
