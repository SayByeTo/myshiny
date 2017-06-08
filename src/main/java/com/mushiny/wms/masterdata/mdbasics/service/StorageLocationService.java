package com.mushiny.wms.masterdata.mdbasics.service;

import com.mushiny.wms.common.service.BaseService;
import com.mushiny.wms.masterdata.mdbasics.crud.dto.StorageLocationDTO;

import java.util.List;

public interface StorageLocationService extends BaseService<StorageLocationDTO> {

    List<StorageLocationDTO> getByClientId(String clientId);
}
