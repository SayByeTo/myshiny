package com.mushiny.wms.masterdata.obbasics.repository;

import com.mushiny.wms.common.respository.BaseRepository;
import com.mushiny.wms.masterdata.obbasics.domain.ProcessPath;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProcessPathRepository extends BaseRepository<ProcessPath, String> {

    @Query("select p from ProcessPath p where p.name = :name and p.warehouseId = :warehouse")
    ProcessPath getByName(@Param("warehouse") String warehouse, @Param("name") String name);
}
