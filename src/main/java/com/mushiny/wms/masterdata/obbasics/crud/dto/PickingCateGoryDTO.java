package com.mushiny.wms.masterdata.obbasics.crud.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mushiny.wms.common.crud.dto.BaseClientAssignedDTO;
import com.mushiny.wms.common.crud.dto.BaseDTO;
import com.mushiny.wms.masterdata.obbasics.domain.PickingCateGory;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

public class PickingCateGoryDTO extends BaseClientAssignedDTO {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String name;

    private String description;

    private String cateGoryType;

    private String consumerType;

    @NotNull
    private int orderIndex = 0;

    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String processPathId;

    private ProcessPathDTO processPath;

    private List<PickingCateGoryPositionDTO> positions = new ArrayList<>();

    public PickingCateGoryDTO() {
    }

    public PickingCateGoryDTO(PickingCateGory entity) {
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

    public String getProcessPathId() {
        return processPathId;
    }

    public void setProcessPathId(String processPathId) {
        this.processPathId = processPathId;
    }

    public ProcessPathDTO getProcessPath() {
        return processPath;
    }

    public void setProcessPath(ProcessPathDTO processPath) {
        this.processPath = processPath;
    }

    public List<PickingCateGoryPositionDTO> getPositions() {
        return positions;
    }

    public void setPositions(List<PickingCateGoryPositionDTO> positions) {
        this.positions = positions;
    }

    public int getOrderIndex() {
        return orderIndex;
    }

    public void setOrderIndex(int orderIndex) {
        this.orderIndex = orderIndex;
    }
}
