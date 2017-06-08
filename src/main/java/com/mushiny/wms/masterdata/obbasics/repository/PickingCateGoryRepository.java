package com.mushiny.wms.masterdata.obbasics.repository;

import com.mushiny.wms.common.respository.BaseRepository;
import com.mushiny.wms.masterdata.obbasics.domain.PickingCateGory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PickingCateGoryRepository extends BaseRepository<PickingCateGory, String> {

    @Query("select b from PickingCateGory b " +
            " where b.warehouseId = :warehouse and b.clientId = :client and b.name = :name")
    PickingCateGory getByName(@Param("warehouse") String warehouse,
                              @Param("client") String client,
                           @Param("name") String name);
}
