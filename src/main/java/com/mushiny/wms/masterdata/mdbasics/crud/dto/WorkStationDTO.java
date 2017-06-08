package com.mushiny.wms.masterdata.mdbasics.crud.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mushiny.wms.common.crud.dto.BaseWarehouseAssignedDTO;
import com.mushiny.wms.masterdata.mdbasics.domain.WorkStation;
import com.mushiny.wms.masterdata.obbasics.crud.dto.PickPackWallDTO;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class WorkStationDTO extends BaseWarehouseAssignedDTO {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String name;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String typeId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pickPackWallId;

    private boolean fixedScanner;

    private WorkStationTypeDTO workstationType;

    private PickPackWallDTO pickPackWall;

    private List<WorkStationPositionDTO> positions = new ArrayList<>();

    public WorkStationDTO() {
    }

    public WorkStationDTO(WorkStation entity) {
        super(entity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public boolean getFixedScanner() {
        return fixedScanner;
    }

    public void setFixedScanner(boolean fixedScanner) {
        this.fixedScanner = fixedScanner;
    }

    public List<WorkStationPositionDTO> getPositions() {
        return positions;
    }

    public void setPositions(List<WorkStationPositionDTO> positions) {
        this.positions = positions;
    }

    public WorkStationTypeDTO getWorkstationType() {
        return workstationType;
    }

    public void setWorkstationType(WorkStationTypeDTO workstationType) {
        this.workstationType = workstationType;
    }

    public String getPickPackWallId() {
        return pickPackWallId;
    }

    public void setPickPackWallId(String pickPackWallId) {
        this.pickPackWallId = pickPackWallId;
    }

    public PickPackWallDTO getPickPackWall() {
        return pickPackWall;
    }

    public void setPickPackWall(PickPackWallDTO pickPackWall) {
        this.pickPackWall = pickPackWall;
    }
}
