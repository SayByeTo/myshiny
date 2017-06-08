package com.mushiny.wms.masterdata.obbasics.repository;

import com.mushiny.wms.common.respository.BaseRepository;
import com.mushiny.wms.masterdata.obbasics.domain.PickingArea;
import com.mushiny.wms.masterdata.general.domain.Client;
import com.mushiny.wms.masterdata.general.domain.Warehouse;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PickingAreaRepository extends BaseRepository<PickingArea, String> {

    @Query("select p from PickingArea p " +
            " where p.warehouseId = :warehouse and p.clientId = :client and p.name = :name")
    PickingArea getByName(@Param("warehouse") String warehouse,
                          @Param("client") String client,
                          @Param("name") String name);

    @Query("select p from PickingArea p where p.warehouseId = :warehouse ")
    List<PickingArea> getByWarehouse(@Param("warehouse") Warehouse warehouse);
}
