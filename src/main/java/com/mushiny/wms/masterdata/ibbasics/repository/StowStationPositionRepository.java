package com.mushiny.wms.masterdata.ibbasics.repository;

import com.mushiny.wms.common.respository.BaseRepository;
import com.mushiny.wms.masterdata.ibbasics.domain.StowStationPosition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StowStationPositionRepository extends BaseRepository<StowStationPosition, String> {

//    @Query(" select a from StowStation a " +
//            " where a.warehouseId = :warehouse and a.name = :name")
//    StowStation getByName(@Param("warehouse") String warehouse,
//                          @Param("name") String name);
}
