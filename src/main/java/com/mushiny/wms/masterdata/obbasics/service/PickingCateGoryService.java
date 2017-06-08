package com.mushiny.wms.masterdata.obbasics.service;

import com.mushiny.wms.common.service.BaseService;
import com.mushiny.wms.masterdata.obbasics.crud.dto.PickingCateGoryDTO;

import java.util.List;

public interface PickingCateGoryService extends BaseService<PickingCateGoryDTO> {

    List<PickingCateGoryDTO> getAll();
}
