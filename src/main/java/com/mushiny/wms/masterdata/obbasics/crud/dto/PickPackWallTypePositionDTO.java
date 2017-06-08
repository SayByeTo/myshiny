package com.mushiny.wms.masterdata.obbasics.crud.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mushiny.wms.common.crud.dto.BaseWarehouseAssignedDTO;
import com.mushiny.wms.masterdata.obbasics.domain.PickPackWallTypePosition;

import javax.validation.constraints.NotNull;

public class PickPackWallTypePositionDTO extends BaseWarehouseAssignedDTO {
    private static final long serialVersionUID = 1L;



    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String fieldTypeId;

    private PickPackFieldTypeDTO pickPackFieldType;

    @NotNull
    private Integer orderIndex;

    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String wallTypeId;

    private PickPackWallTypeDTO pickPackWallType;

    public PickPackWallTypePositionDTO() {
    }

    public PickPackWallTypePositionDTO(PickPackWallTypePosition entity) {
        super(entity);
    }

    public Integer getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(Integer orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getFieldTypeId() {
        return fieldTypeId;
    }

    public void setFieldTypeId(String fieldTypeId) {
        this.fieldTypeId = fieldTypeId;
    }

    public PickPackFieldTypeDTO getPickPackFieldType() {
        return pickPackFieldType;
    }

    public void setPickPackFieldType(PickPackFieldTypeDTO pickPackFieldType) {
        this.pickPackFieldType = pickPackFieldType;
    }

    public String getWallTypeId() {
        return wallTypeId;
    }

    public void setWallTypeId(String wallTypeId) {
        this.wallTypeId = wallTypeId;
    }

    public PickPackWallTypeDTO getPickPackWallType() {
        return pickPackWallType;
    }

    public void setPickPackWallType(PickPackWallTypeDTO pickPackWallType) {
        this.pickPackWallType = pickPackWallType;
    }
}
