package com.mushiny.wms.masterdata.ibbasics.repository;

import com.mushiny.wms.common.respository.BaseRepository;
import com.mushiny.wms.masterdata.ibbasics.domain.UnitLoad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UnitLoadRepository extends BaseRepository<UnitLoad, String> {

    @Query("select u from UnitLoad u where u.label = :label")
    UnitLoad getByLabel(@Param("label") String label);
//
//    @Query("select u from UnitLoad u where u.container = :container")
//    UnitLoad getByContainer(@Param("container")Container container);
//
//    @Query("select u from UnitLoad u where u.storageLocation = :storageLocation")
//    UnitLoad getByStorageLocation(@Param("storageLocation")StorageLocation storageLocation);
}
