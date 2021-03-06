package com.mushiny.wms.masterdata.ibbasics.domain;

import com.mushiny.wms.common.entity.BaseClientAssignedEntity;

import javax.persistence.*;
import java.time.LocalDate;
//import java.time.ZonedDateTime;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "IB_ADVICEREQUEST")
public class AdviceRequest extends BaseClientAssignedEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "ADVICE_NO", nullable = false, unique = true)
    private String adviceNo;

    @Column(name = "ADVICE_STATE")
    private String adviceState;

    @Column(name = "EXPECTED_DELIVERY")
    private LocalDate expectedDelivery;

    @Column(name = "EXPIRE_BATCH")
    private boolean expireBatch;

    @Column(name = "EXTERNAL_NO")
    private String externalNo;

    @Column(name = "EXTERNAL_ID")
    private String externalId;

    @Column(name = "FINISH_DATE")
    private LocalDateTime finishDate;

    @Column(name = "PROCESS_DATE")
    private LocalDateTime processDate;

    @OrderBy("positionNo")
    @OneToMany(mappedBy = "adviceRequest", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<AdviceRequestPosition> positions = new ArrayList<>();

    public void addPosition(AdviceRequestPosition position) {
        getPositions().add(position);
        position.setAdviceRequest(this);
    }

    public String getAdviceNo() {
        return adviceNo;
    }

    public void setAdviceNo(String adviceNo) {
        this.adviceNo = adviceNo;
    }

    public String getAdviceState() {
        return adviceState;
    }

    public void setAdviceState(String adviceState) {
        this.adviceState = adviceState;
    }

    public LocalDate getExpectedDelivery() {
        return expectedDelivery;
    }

    public void setExpectedDelivery(LocalDate expectedDelivery) {
        this.expectedDelivery = expectedDelivery;
    }

    public boolean isExpireBatch() {
        return expireBatch;
    }

    public void setExpireBatch(boolean expireBatch) {
        this.expireBatch = expireBatch;
    }

    public String getExternalNo() {
        return externalNo;
    }

    public void setExternalNo(String externalNo) {
        this.externalNo = externalNo;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public LocalDateTime getFinishDate() {
        return finishDate;
    }

    public void setFinishDate(LocalDateTime finishDate) {
        this.finishDate = finishDate;
    }

    public LocalDateTime getProcessDate() {
        return processDate;
    }

    public void setProcessDate(LocalDateTime processDate) {
        this.processDate = processDate;
    }

    public List<AdviceRequestPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<AdviceRequestPosition> positions) {
        this.positions = positions;
    }
}
