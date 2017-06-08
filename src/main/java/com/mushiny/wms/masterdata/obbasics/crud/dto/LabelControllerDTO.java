package com.mushiny.wms.masterdata.obbasics.crud.dto;

import com.mushiny.wms.common.crud.dto.BaseClientAssignedDTO;
import com.mushiny.wms.common.crud.dto.BaseWarehouseAssignedDTO;
import com.mushiny.wms.masterdata.obbasics.domain.LabelController;

import javax.validation.constraints.NotNull;

public class LabelControllerDTO extends BaseWarehouseAssignedDTO {
    private static final long serialVersionUID = 1L;


    @NotNull
    private String name;

    public LabelControllerDTO() {
    }

    public LabelControllerDTO(LabelController entity) {
        super(entity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
