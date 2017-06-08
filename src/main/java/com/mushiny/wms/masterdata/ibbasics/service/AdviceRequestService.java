package com.mushiny.wms.masterdata.ibbasics.service;

import com.mushiny.wms.common.service.BaseService;
import com.mushiny.wms.masterdata.ibbasics.crud.dto.AdviceRequestDTO;

import java.util.List;

public interface AdviceRequestService extends BaseService<AdviceRequestDTO> {

    List<AdviceRequestDTO> getByClientId(String clientId);
}
