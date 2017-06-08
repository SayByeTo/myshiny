package com.mushiny.wms.masterdata.mdbasics.repository;

import com.mushiny.wms.common.respository.BaseRepository;
import com.mushiny.wms.masterdata.mdbasics.domain.Pod;
import com.mushiny.wms.masterdata.mdbasics.domain.StorageLocation;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StorageLocationRepository extends BaseRepository<StorageLocation, String> {

    @Modifying
    @Query(" update StorageLocation set orderIndex = orderIndex + :addIndex " +
            " where orderIndex >= :binIndex and warehouseId = :warehouse ")
    void updateCreateBinOrderIndex(@Param("addIndex") int addIndex,
                                   @Param("binIndex") int binIndex,
                                   @Param("warehouse") String warehouse);

    @Modifying
    @Query(" update StorageLocation set orderIndex = orderIndex - :subIndex " +
            " where orderIndex > :binIndex and warehouseId = :warehouse ")
    void updateDeleteBinOrderIndex(@Param("subIndex") int subIndex,
                                   @Param("binIndex") int binIndex,
                                   @Param("warehouse") String warehouse);

    @Query(" select s from StorageLocation s " +
            " where s.warehouseId = :warehouse and s.name =:name")
    StorageLocation getByName(@Param("warehouse") String warehouse,
                              @Param("name") String name);

    @Query(" select s from StorageLocation s where s.pod = :pod")
    List<StorageLocation> getByPod(@Param("pod") Pod pod);

    @Query(" select coalesce(min(s.orderIndex), 0) from StorageLocation s where s.pod = :pod ")
    Integer getPodMinIndex(@Param("pod") Pod pod);

    @Query(" select coalesce(max(s.orderIndex), 0) from StorageLocation s where s.pod = :pod ")
    Integer getPodMaxIndex(@Param("pod") Pod pod);
}
