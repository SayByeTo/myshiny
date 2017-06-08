package com.mushiny.wms.masterdata.obbasics.crud.dto;

import com.mushiny.wms.common.crud.dto.BaseClientAssignedDTO;
import com.mushiny.wms.common.crud.dto.BaseWarehouseAssignedDTO;
import com.mushiny.wms.masterdata.obbasics.domain.PickPackCellType;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PickPackCellTypeDTO extends BaseWarehouseAssignedDTO {
    private static final long serialVersionUID = 1L;


    @NotNull
    private String name;

    private String description;

    private BigDecimal height = BigDecimal.ZERO;

    private BigDecimal width = BigDecimal.ZERO;

    private BigDecimal depth = BigDecimal.ZERO;

    private BigDecimal volume;

    private BigDecimal liftingCapacity;

    public PickPackCellTypeDTO() {
    }

    public PickPackCellTypeDTO(PickPackCellType entity) {
        super(entity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWidth() {
        return width;
    }

    public void setWidth(BigDecimal width) {
        this.width = width;
    }

    public BigDecimal getDepth() {
        return depth;
    }

    public void setDepth(BigDecimal depth) {
        this.depth = depth;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getLiftingCapacity() {
        return liftingCapacity;
    }

    public void setLiftingCapacity(BigDecimal liftingCapacity) {
        this.liftingCapacity = liftingCapacity;
    }
}
