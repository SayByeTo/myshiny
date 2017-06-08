package com.mushiny.wms.masterdata.obbasics.domain;

import com.mushiny.wms.common.entity.BaseWarehouseAssignedEntity;

import javax.persistence.*;

@Entity
@Table(name = "OB_PICKPACKFIELDTYPE")
public class PickPackFieldType extends BaseWarehouseAssignedEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "NUMBER_OF_COLUMNS")
    private int numberOfColumns = 0;

    @Column(name = "NUMBER_OF_ROWS")
    private int numberOfRows = 0;

    @ManyToOne(optional = false)
    @JoinColumn(name = "PICKPACKCELLTYPE_ID", nullable = false)
    private PickPackCellType pickPackCellType;

    @Column(name = "FIELD_INDEX", nullable = false)
    private int fieldIndex = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public int getFieldIndex() {
        return fieldIndex;
    }

    public void setFieldIndex(int fieldIndex) {
        this.fieldIndex = fieldIndex;
    }

    public PickPackCellType getPickPackCellType() {
        return pickPackCellType;
    }

    public void setPickPackCellType(PickPackCellType pickPackCellType) {
        this.pickPackCellType = pickPackCellType;
    }

}
