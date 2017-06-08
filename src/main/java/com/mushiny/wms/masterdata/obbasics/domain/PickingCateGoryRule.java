package com.mushiny.wms.masterdata.obbasics.domain;

import com.mushiny.wms.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "OB_PICKINGCATEGORYRULE")
public class PickingCateGoryRule extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "RULE_KEY", nullable = false)
    private String ruleKey;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "COMPARISON_TYPE", nullable = false)
    private String comparisonType;

    @OrderBy("orderIndex")
    @OneToMany(mappedBy = "pickingCateGoryRule", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<PickingCateGoryRulePosition> positions = new ArrayList<>();

    public void addPosition(PickingCateGoryRulePosition position) {
        getPositions().add(position);
        position.setPickingCateGoryRule(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<PickingCateGoryRulePosition> getPositions() {
        return positions;
    }

    public void setPositions(List<PickingCateGoryRulePosition> positions) {
        this.positions = positions;
    }

    public String getRuleKey() {
        return ruleKey;
    }

    public void setRuleKey(String ruleKey) {
        this.ruleKey = ruleKey;
    }

    public String getComparisonType() {
        return comparisonType;
    }

    public void setComparisonType(String comparisonType) {
        this.comparisonType = comparisonType;
    }
}
