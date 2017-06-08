package com.mushiny.wms.masterdata.mdbasics.service;

import com.mushiny.wms.common.service.BaseService;
import com.mushiny.wms.masterdata.mdbasics.crud.dto.ItemDataDTO;

import java.util.List;

public interface ItemDataService extends BaseService<ItemDataDTO> {

    List<ItemDataDTO> getByClientId(String clientId);
}
