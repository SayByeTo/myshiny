package com.mushiny.wms.masterdata.ibbasics.repository;

import com.mushiny.wms.common.respository.BaseRepository;
import com.mushiny.wms.masterdata.ibbasics.domain.ReceiveStation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ReceiveStationRepository extends BaseRepository<ReceiveStation, String> {

    @Query(" select r from ReceiveStation r where r.warehouseId = :warehouse and r.name = :name ")
    ReceiveStation getByName(@Param("warehouse") String warehouse,
                             @Param("name") String name);
}
