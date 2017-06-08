package com.mushiny.wms.masterdata.obbasics.service;

import com.mushiny.wms.common.service.BaseService;
import com.mushiny.wms.masterdata.obbasics.crud.dto.PickingCateGoryRuleDTO;

import java.util.List;

public interface PickingCateGoryRuleService extends BaseService<PickingCateGoryRuleDTO> {

    List<PickingCateGoryRuleDTO> getAll();
}
