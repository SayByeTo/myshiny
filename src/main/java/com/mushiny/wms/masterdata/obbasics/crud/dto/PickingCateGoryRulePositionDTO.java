package com.mushiny.wms.masterdata.obbasics.crud.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mushiny.wms.common.crud.dto.BaseDTO;
import com.mushiny.wms.masterdata.obbasics.domain.PickingCateGoryRulePosition;

import javax.validation.constraints.NotNull;

public class PickingCateGoryRulePositionDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String positionNo;

    @NotNull
    private int orderIndex = 0;

    @NotNull
    private String operator;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pickingCateGoryRuleId;


    private PickingCateGoryRuleDTO pickingCateGoryRule;

    public PickingCateGoryRulePositionDTO() {
    }

    public PickingCateGoryRulePositionDTO(PickingCateGoryRulePosition entity) {
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
}



