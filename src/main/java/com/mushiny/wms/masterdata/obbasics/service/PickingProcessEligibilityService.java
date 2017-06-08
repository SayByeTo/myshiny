package com.mushiny.wms.masterdata.obbasics.service;

import com.mushiny.wms.masterdata.general.crud.dto.UserDTO;

import java.util.List;

public interface PickingProcessEligibilityService {

    void createPickingAreaEligibility(String processPathId, List<String> userIds);

    List<UserDTO> getAssignedUserByProcessPathId(String processPathId);

    List<UserDTO> getUnassignedUserByProcessPathId(String processPathId);
}
