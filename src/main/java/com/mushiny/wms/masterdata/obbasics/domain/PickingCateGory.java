package com.mushiny.wms.masterdata.obbasics.domain;

import com.mushiny.wms.common.entity.BaseClientAssignedEntity;
import com.mushiny.wms.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "OB_PICKINGCATEGORY", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "NAME", "CLIENT_ID", "WAREHOUSE_ID"})
})
public class PickingCateGory extends BaseClientAssignedEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "CATEGORY_TYPE")
    private String cateGoryType;

    @Column(name = "CONSUMER_TYPE")
    private String consumerType;

    @Column(name = "ORDER_INDEX", nullable = false)
    private int orderIndex = 0;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PROCESSPATH_ID", nullable = false)
    private ProcessPath processPath;

    @OrderBy("orderIndex")
    @OneToMany(mappedBy = "pickingCateGory", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<PickingCateGoryPosition> positions = new ArrayList<>();

    public void addPosition(PickingCateGoryPosition position) {
        getPositions().add(position);
        position.setPickingCateGory(this);
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCateGoryType() {
        return cateGoryType;
    }

    public void setCateGoryType(String cateGoryType) {
        this.cateGoryType = cateGoryType;
    }

    public String getConsumerType() {
        return consumerType;
    }

    public void setConsumerType(String consumerType) {
        this.consumerType = consumerType;
    }

    public ProcessPath getProcessPath() {
        return processPath;
    }

    public void setProcessPath(ProcessPath processPath) {
        this.processPath = processPath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PickingCateGoryPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<PickingCateGoryPosition> positions) {
        this.positions = positions;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }
}
