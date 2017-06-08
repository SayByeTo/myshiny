package com.mushiny.wms.masterdata.obbasics.domain;

import com.mushiny.wms.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "OB_PICKINGCATEGORYRULEPOSITION")
public class PickingCateGoryRulePosition extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "POSITION_NO", nullable = false)
    private String positionNo;

    @Column(name = "ORDER_INDEX", nullable = false)
    private int orderIndex = 0;

    @Column(name = "OPERATOR", nullable = false)
    private String operator;

    @ManyToOne(optional = false)
    @JoinColumn(name = "RULE_ID", nullable = false)
    private PickingCateGoryRule pickingCateGoryRule;


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

    public PickingCateGoryRule getPickingCateGoryRule() {
        return pickingCateGoryRule;
    }

    public void setPickingCateGoryRule(PickingCateGoryRule pickingCateGoryRule) {
        this.pickingCateGoryRule = pickingCateGoryRule;
    }
}
