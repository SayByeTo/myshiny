package com.mushiny.wms.masterdata.ibbasics.repository;

import com.mushiny.wms.common.respository.BaseRepository;
import com.mushiny.wms.masterdata.ibbasics.domain.AdviceRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdviceRequestRepository extends BaseRepository<AdviceRequest, String> {

    @Query("select a from AdviceRequest a where a.adviceNo = :adviceNo")
    AdviceRequest getByAdviceNo(@Param("adviceNo") String adviceNo);
}
