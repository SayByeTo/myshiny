package com.mushiny.wms.masterdata.obbasics.repository;

import com.mushiny.wms.common.respository.BaseRepository;
import com.mushiny.wms.masterdata.obbasics.domain.DigitalLabel;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DigitalLabelRepository extends BaseRepository<DigitalLabel, String> {

    @Query("select b from DigitalLabel b " +
            " where b.warehouseId = :warehouse and b.name = :name")
    DigitalLabel getByName(@Param("warehouse") String warehouse,
                              @Param("name") String name);

    @Query("select b from DigitalLabel b " +
            " where b.labelController.id in :labelId ")
    List<DigitalLabel> getByLabel(@Param("labelId") List<String> labelId);

}
