package com.mushiny.wms.masterdata.ibbasics.crud.dto;

import com.mushiny.wms.common.crud.dto.BaseClientAssignedDTO;
import com.mushiny.wms.masterdata.ibbasics.domain.ReplenishStrategy;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ReplenishStrategyDTO extends BaseClientAssignedDTO {
    private static final long serialVersionUID = 1L;

    private BigDecimal minSkuUnits;

    @NotNull
    private BigDecimal shipmentDay;

    @NotNull
    private BigDecimal unitsShipment;

    private String replenishTrigger;

    @NotNull
    private String fudStrategy;

    private String replenishPadTime;

    @NotNull
    private String receivePrime;

    private String receivePrimeStrategy;

    @NotNull
    private String skuStowRule;

    @NotNull
    private String skuMaxType;

    private String stowingType;

    private String problemLocation;

    public ReplenishStrategyDTO() {
    }

    public ReplenishStrategyDTO(ReplenishStrategy entity) {
        super(entity);
    }

    public BigDecimal getMinSkuUnits() {
        return minSkuUnits;
    }

    public void setMinSkuUnits(BigDecimal minSkuUnits) {
        this.minSkuUnits = minSkuUnits;
    }

    public BigDecimal getShipmentDay() {
        return shipmentDay;
    }

    public void setShipmentDay(BigDecimal shipmentDay) {
        this.shipmentDay = shipmentDay;
    }

    public BigDecimal getUnitsShipment() {
        return unitsShipment;
    }

    public void setUnitsShipment(BigDecimal unitsShipment) {
        this.unitsShipment = unitsShipment;
    }

    public String getReplenishTrigger() {
        return replenishTrigger;
    }

    public void setReplenishTrigger(String replenishTrigger) {
        this.replenishTrigger = replenishTrigger;
    }

    public String getFudStrategy() {
        return fudStrategy;
    }

    public void setFudStrategy(String fudStrategy) {
        this.fudStrategy = fudStrategy;
    }

    public String getReplenishPadTime() {
        return replenishPadTime;
    }

    public void setReplenishPadTime(String replenishPadTime) {
        this.replenishPadTime = replenishPadTime;
    }

    public String getReceivePrime() {
        return receivePrime;
    }

    public void setReceivePrime(String receivePrime) {
        this.receivePrime = receivePrime;
    }

    public String getReceivePrimeStrategy() {
        return receivePrimeStrategy;
    }

    public void setReceivePrimeStrategy(String receivePrimeStrategy) {
        this.receivePrimeStrategy = receivePrimeStrategy;
    }

    public String getSkuStowRule() {
        return skuStowRule;
    }

    public void setSkuStowRule(String skuStowRule) {
        this.skuStowRule = skuStowRule;
    }

    public String getSkuMaxType() {
        return skuMaxType;
    }

    public void setSkuMaxType(String skuMaxType) {
        this.skuMaxType = skuMaxType;
    }

    public String getStowingType() {
        return stowingType;
    }

    public void setStowingType(String stowingType) {
        this.stowingType = stowingType;
    }

    public String getProblemLocation() {
        return problemLocation;
    }

    public void setProblemLocation(String problemLocation) {
        this.problemLocation = problemLocation;
    }
}
