package com.mushiny.wms.masterdata.mdbasics.domain;

import com.mushiny.wms.common.entity.BaseWarehouseAssignedEntity;
import com.mushiny.wms.masterdata.ibbasics.domain.ReceiveThreshold;
import com.mushiny.wms.masterdata.obbasics.domain.PickPackWall;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "MD_WORKSTATION")
public class WorkStation extends BaseWarehouseAssignedEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "NAME", nullable = false)
    private String name;

    @ManyToOne(optional = false)
    @JoinColumn(name = "TYPE_ID", nullable = false)
    private WorkStationType type;

    @ManyToOne
    @JoinColumn(name = "PICKPACKWALL_ID")
    private PickPackWall pickPackWall;

    @Column(name = "FIXED_SCANNER")
    private boolean fixedScanner;

    @ManyToMany
    @OrderBy("name")
    @JoinTable(
            name = "MD_HARDWARE_WORKSTATION",
            joinColumns = @JoinColumn(name = "WORKSTATION_ID"),
            inverseJoinColumns = @JoinColumn(name = "HARDWARE_ID"))
    private Set<HardWare> hardWares = new HashSet<>();

    @OrderBy("positionNo")
    @OneToMany(mappedBy = "workStation", cascade = {CascadeType.ALL}, orphanRemoval = true)
    private List<WorkStationPosition> positions = new ArrayList<>();

    public void addPosition(WorkStationPosition position) {
        getPositions().add(position);
        position.setWorkStation(this);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public WorkStationType getType() {
        return type;
    }

    public void setType(WorkStationType type) {
        this.type = type;
    }

    public boolean getFixedScanner() {
        return fixedScanner;
    }

    public void setFixedScanner(boolean fixedScanner) {
        this.fixedScanner = fixedScanner;
    }

    public List<WorkStationPosition> getPositions() {
        return positions;
    }

    public void setPositions(List<WorkStationPosition> positions) {
        this.positions = positions;
    }

    public PickPackWall getPickPackWall() {
        return pickPackWall;
    }

    public void setPickPackWall(PickPackWall pickPackWall) {
        this.pickPackWall = pickPackWall;
    }

    public Set<HardWare> getHardWares() {
        return hardWares;
    }

    public void setHardWares(Set<HardWare> hardWares) {
        this.hardWares = hardWares;
    }
}
