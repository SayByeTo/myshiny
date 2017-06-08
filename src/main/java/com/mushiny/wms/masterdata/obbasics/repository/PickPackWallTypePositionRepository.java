package com.mushiny.wms.masterdata.obbasics.repository;

import com.mushiny.wms.common.respository.BaseRepository;
import com.mushiny.wms.masterdata.obbasics.domain.PickPackFieldType;
import com.mushiny.wms.masterdata.obbasics.domain.PickPackWallType;
import com.mushiny.wms.masterdata.obbasics.domain.PickPackWallTypePosition;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PickPackWallTypePositionRepository extends BaseRepository<PickPackWallTypePosition, String> {

    @Query("select r from PickPackWallTypePosition r " +
            " where r.pickPackWallType = :pickPackWallType order by r.orderIndex")
    List<PickPackWallTypePosition> getByType(@Param("pickPackWallType") PickPackWallType pickPackWallType);

    @Query("select r.pickPackFieldType from PickPackWallTypePosition r " +
            " where r.pickPackWallType.id = :reBinWallType order by r.pickPackFieldType.fieldIndex ")
    List<PickPackFieldType> getByTypeId(@Param("reBinWallType") String reBinWallType);

    @Query("select r from PickPackWallTypePosition r " +
            " where r.pickPackFieldType.id in :pickPackFieldType order by r.orderIndex")
    List<PickPackWallTypePosition> getByFileType(@Param("pickPackFieldType") List pickPackFieldType);
}
