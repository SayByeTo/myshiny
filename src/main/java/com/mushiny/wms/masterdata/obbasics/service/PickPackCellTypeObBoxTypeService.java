package com.mushiny.wms.masterdata.obbasics.service;

import com.mushiny.wms.masterdata.obbasics.crud.dto.BoxTypeDTO;
import com.mushiny.wms.masterdata.obbasics.crud.dto.PickPackCellTypeDTO;

import java.util.List;

public interface PickPackCellTypeObBoxTypeService {

    void createPickPackCellTypeObBoxType(String pickingAreaId, List<String> userIds);

    List<BoxTypeDTO> getList(String clientId);

    List<PickPackCellTypeDTO> getAssignedUserByReBinCellTypeId(String pickingAreaId);

    List<PickPackCellTypeDTO> getUnassignedUserByReBinCellTypeId(String pickingAreaId);

}
