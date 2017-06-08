package com.mushiny.wms.masterdata.obbasics.domain;


import com.mushiny.wms.common.entity.BaseWarehouseAssignedEntity;
import com.mushiny.wms.masterdata.general.domain.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "OB_PROCESSPATH", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"NAME", "WAREHOUSE_ID"})
})
public class ProcessPath extends BaseWarehouseAssignedEntity {

    private static final long serialVersionUID = 1L;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "SORT_CODE", nullable = false)
    private String sortCode;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "REGENERATE_SHORTED_PICKS")
    private String regenerateShortedPicks;

    @Column(name = "MIN_SHIPMENTS_PER_BATCH")
    private int minShipmentsPerBatch = 0;

    @Column(name = "MAX_SHIPMENTS_PER_BATCH")
    private int maxShipmentsPerBatch = 0;

    @Column(name = "MIN_ITEMS_PER_BATCH")
    private int minItemsPerBatch = 0;

    @Column(name = "MAX_ITEMS_PER_BATCH")
    private int maxItemsPerBatch = 0;

    @Column(name = "COLLATE_TYPE")
    private String collateType;

    @Column(name = "COLLATE_DOCUMENTS")
    private String collateDocuments;

    @Column(name = "TOTE_BUFFER_LIMITED")
    private String toteBufferLimited;

    @Column(name = "REJECT_REASONS")
    private String rejectReasons;

    @Column(name = "ALLOWED_PICK_TYPES")
    private String allowedPickTypes;

    @Column(name = "ALLOWED_CONTAINER_TYPES")
    private String allowedContainerTypes;

    @Column(name = "LABOR_UNIT_CLASS")
    private String laborUnitClass;

    @Column(name = "WEIGHT_LIMIT")
    private String weightLimit;

    @Column(name = "SCAN_BIN_FIRST", nullable = false)
    private boolean scanBinFirst = false;

    @Column(name = "COLOR_BASED_BIN_PICKING", nullable = false)
    private boolean colorBasedBinPicking = false;

    @Column(name = "COLOR_BASED_ITEM_PICKING", nullable = false)
    private boolean colorBasedItemPicking = false;

    @Column(name = "ONE_ITEM_PER_TOTE", nullable = false)
    private boolean oneItemPerTote = false;

    @Column(name = "REQUIRE_NEW_TOTE_ON_PICK_AREA_CHANGE", nullable = false)
    private boolean requireNewToteOnPickAreaChange = false;

    @Column(name = "IDLE_TIMEOUT_IN_SECONDS", nullable = false)
    private int idleTimeoutInSecond = 0;

    @Column(name = "TOTE_RESCAN", nullable = false)
    private String toteRescan;

    @Column(name = "PICK_TO_CONTAINER_IMAGE", nullable = false)
    private String pickToContainerImage;

    @Column(name = "TARGET_PICK_RATE")
    private int targetPickRate = 0;

    @Column(name = "PROCESS_PAD")
    private int processPad = 0;

    @Column(name = "TOTE_LIMIT")
    private int toteLimit = 0;

    @Column(name = "BATCH_LIMIT")
    private int batchLimit = 0;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PICKDESTINATION_ID")
    private PickStationType pickStationType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "REBINDESTINATION_ID")
    private ReBinStationType reBinDestination;

    @ManyToOne(optional = false)
    @JoinColumn(name = "REBINWALLTYPE_ID")
    private ReBinWallType reBinWallType;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PACKDESTINATION_ID")
    private PackingStationType packDestination;

    @ManyToOne(optional = false)
    @JoinColumn(name = "TYPE_ID")
    private ProcessPathType processPathType;

    @ManyToMany
    @OrderBy("username")
    @JoinTable(
            name = "OB_PICKINGPROCESSELIGIBILITY",
            joinColumns = @JoinColumn(name = "PROCESSPATH_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private List<User> users = new ArrayList<>();

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

    public ReBinStationType getReBinDestination() {
        return reBinDestination;
    }

    public void setReBinDestination(ReBinStationType reBinDestination) {
        this.reBinDestination = reBinDestination;
    }

    public ReBinWallType getReBinWallType() {
        return reBinWallType;
    }

    public void setReBinWallType(ReBinWallType reBinWallType) {
        this.reBinWallType = reBinWallType;
    }

    public PackingStationType getPackDestination() {
        return packDestination;
    }

    public void setPackDestination(PackingStationType packDestination) {
        this.packDestination = packDestination;
    }

    public ProcessPathType getProcessPathType() {
        return processPathType;
    }

    public void setProcessPathType(ProcessPathType processPathType) {
        this.processPathType = processPathType;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
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

    public PickStationType getPickStationType() {
        return pickStationType;
    }

    public void setPickStationType(PickStationType pickStationType) {
        this.pickStationType = pickStationType;
    }
}
