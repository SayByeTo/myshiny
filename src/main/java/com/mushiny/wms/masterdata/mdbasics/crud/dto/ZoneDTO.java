package com.mushiny.wms.masterdata.mdbasics.crud.dto;

import com.mushiny.wms.common.crud.dto.BaseClientAssignedDTO;
import com.mushiny.wms.masterdata.mdbasics.domain.Zone;

import javax.validation.constraints.NotNull;

public class ZoneDTO extends BaseClientAssignedDTO {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String name;

    private String description;

    public ZoneDTO() {
    }

    public ZoneDTO(Zone entity) {
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
}
