package com.mushiny.wms.masterdata.mdbasics.service;

import com.mushiny.wms.common.service.BaseService;
import com.mushiny.wms.masterdata.mdbasics.crud.dto.ItemDataGlobalDTO;

import java.util.List;

public interface ItemDataGlobalService extends BaseService<ItemDataGlobalDTO> {

    List<ItemDataGlobalDTO> getBySkuNo(String skuNo);

    List<ItemDataGlobalDTO> getByClientId(String clientId);
}
