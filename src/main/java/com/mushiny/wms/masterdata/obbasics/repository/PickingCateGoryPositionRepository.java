package com.mushiny.wms.masterdata.obbasics.repository;

import com.mushiny.wms.common.respository.BaseRepository;
import com.mushiny.wms.masterdata.obbasics.domain.PickingCateGoryPosition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PickingCateGoryPositionRepository extends BaseRepository<PickingCateGoryPosition, String> {

    @Query("select b from PickingCateGoryPosition b " +
            " where b.warehouseId = :warehouse and b.clientId = :client and b.positionNo = :positionNo")
    PickingCateGoryPosition getByName(@Param("warehouse") String warehouse,
                              @Param("client") String client,
                              @Param("positionNo") String positionNo);
}
