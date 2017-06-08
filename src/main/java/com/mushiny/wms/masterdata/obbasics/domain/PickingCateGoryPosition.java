package com.mushiny.wms.masterdata.obbasics.domain;

import com.mushiny.wms.common.entity.BaseClientAssignedEntity;
import com.mushiny.wms.common.entity.BaseEntity;

import javax.persistence.*;

@Entity
@Table(name = "OB_PICKINGCATEGORYPOSITION", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "POSITION_NO", "CLIENT_ID", "WAREHOUSE_ID"})
})
public class PickingCateGoryPosition extends BaseClientAssignedEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "POSITION_NO", nullable = false)
    private String positionNo;

    @Column(name = "ORDER_INDEX", nullable = false)
    private int orderIndex = 0;

    @Column(name = "OPERATOR", nullable = false)
    private String operator;

    @Column(name = "COMP_VALUE")
    private String compValue;

    @ManyToOne(optional = false)
    @JoinColumn(name = "RULE_ID", nullable = false)
    private PickingCateGoryRule pickingCateGoryRule;

    @ManyToOne(optional = false)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private PickingCateGory pickingCateGory;


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

    public String getCompValue() {
        return compValue;
    }

    public void setCompValue(String compValue) {
        this.compValue = compValue;
    }

    public PickingCateGory getPickingCateGory() {
        return pickingCateGory;
    }

    public void setPickingCateGory(PickingCateGory pickingCateGory) {
        this.pickingCateGory = pickingCateGory;
    }
}
