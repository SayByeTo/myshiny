package com.mushiny.wms.masterdata.obbasics.repository;

import com.mushiny.wms.common.respository.BaseRepository;
import com.mushiny.wms.masterdata.obbasics.domain.ReBinStation;
import com.mushiny.wms.masterdata.general.domain.Warehouse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReBinStationRepository extends BaseRepository<ReBinStation, String> {

    @Query("select r from ReBinStation r where r.warehouseId = :warehouse and r.name = :name")
    ReBinStation getByName(@Param("warehouse") String warehouse, @Param("name") String name);

    @Query("select r from ReBinStation r where r.reBinStationType.id = :reBinStationTypeId")
    List<ReBinStation> getByReBinStationTypeId(@Param("reBinStationTypeId") String reBinStationTypeId);
}
