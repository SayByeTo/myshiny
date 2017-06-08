package com.mushiny.wms.masterdata.obbasics.crud.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mushiny.wms.common.crud.dto.BaseWarehouseAssignedDTO;
import com.mushiny.wms.masterdata.obbasics.domain.ProcessPath;

import javax.validation.constraints.NotNull;

public class ProcessPathDTO extends BaseWarehouseAssignedDTO {
    private static final long serialVersionUID = 1L;

    @NotNull
    private String name;

    @NotNull
    private String sortCode;

    private String description;

    private String regenerateShortedPicks;

    private int minShipmentsPerBatch = 0;

    private int maxShipmentsPerBatch = 0;

    private int minItemsPerBatch = 0;

    private int maxItemsPerBatch = 0;

    private String collateType;

    private String collateDocuments;

    private String toteBufferLimited;

    private String rejectReasons;

    private String allowedPickTypes;

    private String allowedContainerTypes;

    private String laborUnitClass;

    private String weightLimit;

    private int targetPickRate = 0;

    private int processPad = 0;

    private int toteLimit = 0;

    private int batchLimit = 0;

    @NotNull
    private boolean scanBinFirst = false;

    @NotNull
    private boolean colorBasedBinPicking = false;

    @NotNull
    private boolean colorBasedItemPicking = false;

    @NotNull
    private boolean oneItemPerTote = false;

    @NotNull
    private boolean requireNewToteOnPickAreaChange = false;

    @NotNull
    private int idleTimeoutInSecond = 0;

    @NotNull
    private String toteRescan;

    @NotNull
    private String pickToContainerImage;

    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String pickDestinationId;

    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String rebinDestinationId;

    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String rebinWallTypeId;

    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String packDestinationId;

    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String processPathTypeId;

    private PickStationTypeDTO pickDestination;

    private ReBinStationTypeDTO rebinDestination;

    private ReBinWallTypeDTO rebinWallType;

    private PackingStationTypeDTO packDestination;

    private ProcessPathTypeDTO processPathType;

    public ProcessPathDTO() {
    }

    public ProcessPathDTO(ProcessPath entity) {
        super(entity);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSortCode() {
        return sortCode;
    }

    public void setSortCode(String sortCode) {
        this.sortCode = sortCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRegenerateShortedPicks() {
        return regenerateShortedPicks;
    }

    public void setRegenerateShortedPicks(String regenerateShortedPicks) {
        this.regenerateShortedPicks = regenerateShortedPicks;
    }

    public int getMinShipmentsPerBatch() {
        return minShipmentsPerBatch;
    }

    public void setMinShipmentsPerBatch(int minShipmentsPerBatch) {
        this.minShipmentsPerBatch = minShipmentsPerBatch;
    }

    public int getMaxShipmentsPerBatch() {
        return maxShipmentsPerBatch;
    }

    public void setMaxShipmentsPerBatch(int maxShipmentsPerBatch) {
        this.maxShipmentsPerBatch = maxShipmentsPerBatch;
    }

    public int getMinItemsPerBatch() {
        return minItemsPerBatch;
    }

    public void setMinItemsPerBatch(int minItemsPerBatch) {
        this.minItemsPerBatch = minItemsPerBatch;
    }

    public int getMaxItemsPerBatch() {
        return maxItemsPerBatch;
    }

    public void setMaxItemsPerBatch(int maxItemsPerBatch) {
        this.maxItemsPerBatch = maxItemsPerBatch;
    }

    public String getCollateType() {
        return collateType;
    }

    public void setCollateType(String collateType) {
        this.collateType = collateType;
    }

    public String getCollateDocuments() {
        return collateDocuments;
    }

    public void setCollateDocuments(String collateDocuments) {
        this.collateDocuments = collateDocuments;
    }

    public String getToteBufferLimited() {
        return toteBufferLimited;
    }

    public void setToteBufferLimited(String toteBufferLimited) {
        this.toteBufferLimited = toteBufferLimited;
    }

    public String getRejectReasons() {
        return rejectReasons;
    }

    public void setRejectReasons(String rejectReasons) {
        this.rejectReasons = rejectReasons;
    }

    public String getAllowedPickTypes() {
        return allowedPickTypes;
    }

    public void setAllowedPickTypes(String allowedPickTypes) {
        this.allowedPickTypes = allowedPickTypes;
    }

    public String getAllowedContainerTypes() {
        return allowedContainerTypes;
    }

    public void setAllowedContainerTypes(String allowedContainerTypes) {
        this.allowedContainerTypes = allowedContainerTypes;
    }

    public String getLaborUnitClass() {
        return laborUnitClass;
    }

    public void setLaborUnitClass(String laborUnitClass) {
        this.laborUnitClass = laborUnitClass;
    }

    public String getWeightLimit() {
        return weightLimit;
    }

    public void setWeightLimit(String weightLimit) {
        this.weightLimit = weightLimit;
    }

    public boolean isScanBinFirst() {
        return scanBinFirst;
    }

    public void setScanBinFirst(boolean scanBinFirst) {
        this.scanBinFirst = scanBinFirst;
    }

    public boolean isColorBasedBinPicking() {
        return colorBasedBinPicking;
    }

    public void setColorBasedBinPicking(boolean colorBasedBinPicking) {
        this.colorBasedBinPicking = colorBasedBinPicking;
    }

    public boolean isColorBasedItemPicking() {
        return colorBasedItemPicking;
    }

    public void setColorBasedItemPicking(boolean colorBasedItemPicking) {
        this.colorBasedItemPicking = colorBasedItemPicking;
    }

    public boolean isOneItemPerTote() {
        return oneItemPerTote;
    }

    public void setOneItemPerTote(boolean oneItemPerTote) {
        this.oneItemPerTote = oneItemPerTote;
    }

    public boolean isRequireNewToteOnPickAreaChange() {
        return requireNewToteOnPickAreaChange;
    }

    public void setRequireNewToteOnPickAreaChange(boolean requireNewToteOnPickAreaChange) {
        this.requireNewToteOnPickAreaChange = requireNewToteOnPickAreaChange;
    }

    public int getIdleTimeoutInSecond() {
        return idleTimeoutInSecond;
    }

    public void setIdleTimeoutInSecond(int idleTimeoutInSecond) {
        this.idleTimeoutInSecond = idleTimeoutInSecond;
    }

    public String getToteRescan() {
        return toteRescan;
    }

    public void setToteRescan(String toteRescan) {
        this.toteRescan = toteRescan;
    }

    public String getPickToContainerImage() {
        return pickToContainerImage;
    }

    public void setPickToContainerImage(String pickToContainerImage) {
        this.pickToContainerImage = pickToContainerImage;
    }

    public String getRebinDestinationId() {
        return rebinDestinationId;
    }

    public void setRebinDestinationId(String rebinDestinationId) {
        this.rebinDestinationId = rebinDestinationId;
    }

    public String getRebinWallTypeId() {
        return rebinWallTypeId;
    }

    public void setRebinWallTypeId(String rebinWallTypeId) {
        this.rebinWallTypeId = rebinWallTypeId;
    }

    public String getPackDestinationId() {
        return packDestinationId;
    }

    public void setPackDestinationId(String packDestinationId) {
        this.packDestinationId = packDestinationId;
    }

    public String getProcessPathTypeId() {
        return processPathTypeId;
    }

    public void setProcessPathTypeId(String processPathTypeId) {
        this.processPathTypeId = processPathTypeId;
    }

    public ReBinStationTypeDTO getRebinDestination() {
        return rebinDestination;
    }

    public void setRebinDestination(ReBinStationTypeDTO rebinDestination) {
        this.rebinDestination = rebinDestination;
    }

    public ReBinWallTypeDTO getRebinWallType() {
        return rebinWallType;
    }

    public void setRebinWallType(ReBinWallTypeDTO rebinWallType) {
        this.rebinWallType = rebinWallType;
    }

    public PackingStationTypeDTO getPackDestination() {
        return packDestination;
    }

    public void setPackDestination(PackingStationTypeDTO packDestination) {
        this.packDestination = packDestination;
    }

    public ProcessPathTypeDTO getProcessPathType() {
        return processPathType;
    }

    public void setProcessPathType(ProcessPathTypeDTO processPathType) {
        this.processPathType = processPathType;
    }

    public int getTargetPickRate() {
        return targetPickRate;
    }

    public void setTargetPickRate(int targetPickRate) {
        this.targetPickRate = targetPickRate;
    }

    public int getProcessPad() {
        return processPad;
    }

    public void setProcessPad(int processPad) {
        this.processPad = processPad;
    }

    public int getToteLimit() {
        return toteLimit;
    }

    public void setToteLimit(int toteLimit) {
        this.toteLimit = toteLimit;
    }

    public int getBatchLimit() {
        return batchLimit;
    }

    public void setBatchLimit(int batchLimit) {
        this.batchLimit = batchLimit;
    }

    public String getPickDestinationId() {
        return pickDestinationId;
    }

    public void setPickDestinationId(String pickDestinationId) {
        this.pickDestinationId = pickDestinationId;
    }

    public PickStationTypeDTO getPickDestination() {
        return pickDestination;
    }

    public void setPickDestination(PickStationTypeDTO pickDestination) {
        this.pickDestination = pickDestination;
    }
}
