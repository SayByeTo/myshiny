package com.mushiny.wms.masterdata.obbasics.crud.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mushiny.wms.common.crud.dto.BaseDTO;
import com.mushiny.wms.masterdata.obbasics.domain.PickingCateGoryRule;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class PickingCateGoryRuleDTO extends BaseDTO {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String ruleKey;

    @NotNull
    private String name;

    @NotNull
    private String comparisonType;

    private List<PickingCateGoryRulePositionDTO> positions = new ArrayList<>();

    public PickingCateGoryRuleDTO() {
    }

    public PickingCateGoryRuleDTO(PickingCateGoryRule entity) {
        super(entity);
    }


    public String getRuleKey() {
        return ruleKey;
    }

    public void setRuleKey(String ruleKey) {
        this.ruleKey = ruleKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComparisonType() {
        return comparisonType;
    }

    public void setComparisonType(String comparisonType) {
        this.comparisonType = comparisonType;
    }

    public List<PickingCateGoryRulePositionDTO> getPositions() {
        return positions;
    }

    public void setPositions(List<PickingCateGoryRulePositionDTO> positions) {
        this.positions = positions;
    }
}
