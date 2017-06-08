package com.mushiny.wms.masterdata.mdbasics.service;

import com.mushiny.wms.common.service.BaseService;
import com.mushiny.wms.masterdata.mdbasics.crud.dto.RobotDTO;

import java.util.List;

public interface RobotService extends BaseService<RobotDTO> {

    void enter(String id, String password);
}
