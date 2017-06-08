package com.mushiny.wms.masterdata.obbasics.domain;

import com.mushiny.wms.common.entity.BaseWarehouseAssignedEntity;

import javax.persistence.*;

@Entity
@Table(name = "OB_PICKPACKWALLTYPEPOSITION")
public class PickPackWallTypePosition extends BaseWarehouseAssignedEntity {
    private static final long serialVersionUID = 1L;

    @ManyToOne(optional = false)
    @JoinColumn(name = "FIELDTYPE_ID")
    private PickPackFieldType pickPackFieldType;

    @Column(name = "ORDER_INDEX")
    private Integer orderIndex;

    @ManyToOne(optional = false)
    @JoinColumn(name = "WALLTYPE_ID")
    private PickPackWallType pickPackWallType;

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public PickPackFieldType getPickPackFieldType() {
        return pickPackFieldType;
    }

    public void setPickPackFieldType(PickPackFieldType pickPackFieldType) {
        this.pickPackFieldType = pickPackFieldType;
    }

    public PickPackWallType getPickPackWallType() {
        return pickPackWallType;
    }

    public void setPickPackWallType(PickPackWallType pickPackWallType) {
        this.pickPackWallType = pickPackWallType;
    }
}
