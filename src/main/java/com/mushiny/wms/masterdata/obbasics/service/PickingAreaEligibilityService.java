package com.mushiny.wms.masterdata.obbasics.service;

import com.mushiny.wms.masterdata.general.crud.dto.UserDTO;

import java.util.List;

public interface PickingAreaEligibilityService {

    void createPickingAreaEligibility(String pickingAreaId, List<String> userIds);

    List<UserDTO> getAssignedUserByPickingAreaId(String pickingAreaId);

    List<UserDTO> getUnassignedUserByPickingAreaId(String pickingAreaId);
}
