package com.mushiny.wms.masterdata.mdbasics.repository;

import com.mushiny.wms.common.respository.BaseRepository;
import com.mushiny.wms.masterdata.general.domain.User;
import com.mushiny.wms.masterdata.mdbasics.domain.Area;
import com.mushiny.wms.masterdata.mdbasics.domain.WorkStation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorkStationRepository extends BaseRepository<WorkStation, String> {

    @Query(" select b from WorkStation b " +
            " where b.warehouseId = :warehouse and b.name = :name")
    WorkStation getByName(@Param("warehouse") String warehouse,
                      @Param("name") String name);

    List<WorkStation> findByEntityLock(Integer entityLock);
}
