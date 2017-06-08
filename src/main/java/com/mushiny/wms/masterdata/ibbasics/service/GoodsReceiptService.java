package com.mushiny.wms.masterdata.ibbasics.service;

import com.mushiny.wms.masterdata.ibbasics.crud.dto.GoodsReceiptDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GoodsReceiptService {

    GoodsReceiptDTO retrieve(String id);

    Page<GoodsReceiptDTO> getBySearchTerm(String searchTerm, Pageable pageable);
}
