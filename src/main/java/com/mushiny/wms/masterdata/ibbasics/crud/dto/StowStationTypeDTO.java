package com.mushiny.wms.masterdata.ibbasics.crud.dto;

import com.mushiny.wms.common.crud.dto.BaseClientAssignedDTO;
import com.mushiny.wms.common.crud.dto.BaseWarehouseAssignedDTO;
import com.mushiny.wms.masterdata.ibbasics.domain.StowStationType;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class StowStationTypeDTO extends BaseWarehouseAssignedDTO {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String name;

    private String description;

    private List<StowStationTypePositionDTO> positions = new ArrayList<>();

    public StowStationTypeDTO() {
    }

    public StowStationTypeDTO(StowStationType entity) {
        super(entity);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<StowStationTypePositionDTO> getPositions() {
        return positions;
    }

    public void setPositions(List<StowStationTypePositionDTO> positions) {
        this.positions = positions;
    }
}
