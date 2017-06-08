package com.mushiny.wms.masterdata.obbasics.crud.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mushiny.wms.common.crud.dto.BaseWarehouseAssignedDTO;
import com.mushiny.wms.masterdata.obbasics.domain.PickStationType;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class PickStationTypeDTO extends BaseWarehouseAssignedDTO {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String name;

    private String description;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pickPackWallTypeId;

    private PickPackWallTypeDTO pickPackWallType;

    private List<PickStationTypePositionDTO> positions = new ArrayList<>();

    public PickStationTypeDTO() {
    }

    public PickStationTypeDTO(PickStationType entity) {
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


    public List<PickStationTypePositionDTO> getPositions() {
        return positions;
    }

    public void setPositions(List<PickStationTypePositionDTO> positions) {
        this.positions = positions;
    }

    public String getPickPackWallTypeId() {
        return pickPackWallTypeId;
    }

    public void setPickPackWallTypeId(String pickPackWallTypeId) {
        this.pickPackWallTypeId = pickPackWallTypeId;
    }

    public PickPackWallTypeDTO getPickPackWallType() {
        return pickPackWallType;
    }

    public void setPickPackWallType(PickPackWallTypeDTO pickPackWallType) {
        this.pickPackWallType = pickPackWallType;
    }
}


