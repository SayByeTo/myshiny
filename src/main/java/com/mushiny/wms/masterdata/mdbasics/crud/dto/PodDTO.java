package com.mushiny.wms.masterdata.mdbasics.crud.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mushiny.wms.common.crud.dto.BaseClientAssignedDTO;
import com.mushiny.wms.masterdata.mdbasics.domain.Pod;

import javax.validation.constraints.NotNull;

public class PodDTO extends BaseClientAssignedDTO {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String name;

    private String description;

    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String zoneId;

    private ZoneDTO zone;

    private String placeMark;

    private String sellingDegree;

    @NotNull
    private int podIndex = 0;

    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String podTypeId;

    private PodTypeDTO podType;

    public PodDTO() {
    }

    public PodDTO(Pod entity) {
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

    public PodTypeDTO getPodType() {
        return podType;
    }

    public void setPodType(PodTypeDTO podType) {
        this.podType = podType;
    }

    public String getPodTypeId() {
        return podTypeId;
    }

    public void setPodTypeId(String podTypeId) {
        this.podTypeId = podTypeId;
    }

    public int getPodIndex() {
        return podIndex;
    }

    public void setPodIndex(int podIndex) {
        this.podIndex = podIndex;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getPlaceMark() {
        return placeMark;
    }

    public void setPlaceMark(String placeMark) {
        this.placeMark = placeMark;
    }

    public String getSellingDegree() {
        return sellingDegree;
    }

    public void setSellingDegree(String sellingDegree) {
        this.sellingDegree = sellingDegree;
    }

    public ZoneDTO getZone() {
        return zone;
    }

    public void setZone(ZoneDTO zone) {
        this.zone = zone;
    }
}
