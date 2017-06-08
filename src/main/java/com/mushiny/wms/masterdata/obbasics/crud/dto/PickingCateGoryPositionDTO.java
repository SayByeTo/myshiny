package com.mushiny.wms.masterdata.obbasics.crud.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mushiny.wms.common.crud.dto.BaseClientAssignedDTO;
import com.mushiny.wms.common.crud.dto.BaseDTO;
import com.mushiny.wms.masterdata.obbasics.domain.PickingCateGoryPosition;

import javax.validation.constraints.NotNull;

public class PickingCateGoryPositionDTO extends BaseClientAssignedDTO {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String positionNo;

    @NotNull
    private int orderIndex = 0;

    @NotNull
    private String operator;

    private String compValue;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pickingCateGoryRuleId;

    private PickingCateGoryRuleDTO pickingCateGoryRule;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pickingCateGoryId;

    private PickingCateGoryDTO pickingCateGory;

    public PickingCateGoryPositionDTO() {
    }

    public PickingCateGoryPositionDTO(PickingCateGoryPosition entity) {
        super(entity);
    }

    public String getPositionNo() {
        return positionNo;
    }

    public void setPositionNo(String positionNo) {
        this.positionNo = positionNo;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public PickingCateGoryRuleDTO getPickingCateGoryRule() {
        return pickingCateGoryRule;
    }

    public void setPickingCateGoryRule(PickingCateGoryRuleDTO pickingCateGoryRule) {
        this.pickingCateGoryRule = pickingCateGoryRule;
    }

    public String getPickingCateGoryRuleId() {
        return pickingCateGoryRuleId;
    }

    public void setPickingCateGoryRuleId(String pickingCateGoryRuleId) {
        this.pickingCateGoryRuleId = pickingCateGoryRuleId;
    }

    public String getCompValue() {
        return compValue;
    }

    public void setCompValue(String compValue) {
        this.compValue = compValue;
    }

    public String getPickingCateGoryId() {
        return pickingCateGoryId;
    }

    public void setPickingCateGoryId(String pickingCateGoryId) {
        this.pickingCateGoryId = pickingCateGoryId;
    }

    public PickingCateGoryDTO getPickingCateGory() {
        return pickingCateGory;
    }

    public void setPickingCateGory(PickingCateGoryDTO pickingCateGory) {
        this.pickingCateGory = pickingCateGory;
    }
}



