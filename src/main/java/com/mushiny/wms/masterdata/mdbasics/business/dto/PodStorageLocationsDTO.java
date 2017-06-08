package com.mushiny.wms.masterdata.mdbasics.business.dto;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class PodStorageLocationsDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String clientId;

    @NotNull
    private String zoneId;

    @NotNull
    private int fromPod = 0;

    @NotNull
    private int toPod = 0;

    @NotNull
    private String podTypeId;

    @NotNull
    private String areaId;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getZoneId() {
        return zoneId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public int getFromPod() {
        return fromPod;
    }

    public void setFromPod(int fromPod) {
        this.fromPod = fromPod;
    }

    public int getToPod() {
        return toPod;
    }

    public void setToPod(int toPod) {
        this.toPod = toPod;
    }

    public String getPodTypeId() {
        return podTypeId;
    }

    public void setPodTypeId(String podTypeId) {
        this.podTypeId = podTypeId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

}
