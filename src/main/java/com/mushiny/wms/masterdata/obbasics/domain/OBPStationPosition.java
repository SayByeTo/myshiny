package com.mushiny.wms.masterdata.obbasics.domain;

import com.mushiny.wms.common.entity.BaseWarehouseAssignedEntity;
import com.mushiny.wms.masterdata.mdbasics.domain.WorkStationPosition;

import javax.persistence.*;

@Entity
@Table(name = "OBP_OBPSTATIONPOSITION")
public class OBPStationPosition extends BaseWarehouseAssignedEntity {
    private static final long serialVersionUID = 1L;

    @ManyToOne(optional = false)
    @JoinColumn(name = "WORKSTATIONPOSITION_ID", nullable = false)
    private WorkStationPosition workStationPosition;

    @Column(name = "POSITION_STATE", nullable = false)
    private String positionState;

    @ManyToOne(optional = false)
    @JoinColumn(name = "STATION_ID", nullable = false)
    private OBPStation obpStation;


    public WorkStationPosition getWorkStationPosition() {
        return workStationPosition;
    }

    public void setWorkStationPosition(WorkStationPosition workStationPosition) {
        this.workStationPosition = workStationPosition;
    }

    public String getPositionState() {
        return positionState;
    }

    public void setPositionState(String positionState) {
        this.positionState = positionState;
    }

    public OBPStation getObpStation() {
        return obpStation;
    }

    public void setObpStation(OBPStation obpStation) {
        this.obpStation = obpStation;
    }
}
