package com.mushiny.wms.masterdata.ibbasics.domain;

import com.mushiny.wms.common.entity.BaseClientAssignedEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "IB_REPLENISHSTRATEGY")
public class ReplenishStrategy extends BaseClientAssignedEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "MIN_SKU_UNITS")
    private BigDecimal minSkuUnits;

    @Column(name = "SHIPMENT_DAY", nullable = false)
    private BigDecimal shipmentDay;

    @Column(name = "UNITS_SHIPMENT", nullable = false)
    private BigDecimal unitsShipment;

    @Column(name = "REPLENISH_TRIGGER")
    private String replenishTrigger;

    @Column(name = "FUD_STRATEGY", nullable = false)
    private String fudStrategy;

    @Column(name = "REPLENISH_PAD_TIME")
    private String replenishPadTime;

    @Column(name = "RECEIVE_PRIME", nullable = false)
    private String receivePrime;

    @Column(name = "RECEIVE_PRIME_STRATEGY")
    private String receivePrimeStrategy;

    @Column(name = "SKU_STOW_RULE", nullable = false)
    private String skuStowRule;

    @Column(name = "SKU_MAX_TYPE", nullable = false)
    private String skuMaxType;

    @Column(name = "STOWING_TYPE")
    private String stowingType;

    @Column(name = "PROBLEM_LOCATION")
    private String problemLocation;

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
