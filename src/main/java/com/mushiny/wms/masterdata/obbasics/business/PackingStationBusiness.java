package com.mushiny.wms.masterdata.obbasics.business;

import com.mushiny.wms.common.context.ApplicationContext;
import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.masterdata.mdbasics.domain.WorkStation;
import com.mushiny.wms.masterdata.mdbasics.domain.WorkStationPosition;
import com.mushiny.wms.masterdata.mdbasics.repository.WorkStationPositionRepository;
import com.mushiny.wms.masterdata.mdbasics.repository.WorkStationRepository;
import com.mushiny.wms.masterdata.obbasics.crud.dto.PackingStationDTO;
import com.mushiny.wms.masterdata.obbasics.domain.PackingStation;
import com.mushiny.wms.masterdata.obbasics.domain.PackingStationPosition;
import com.mushiny.wms.masterdata.obbasics.domain.PackingStationType;
import com.mushiny.wms.masterdata.obbasics.domain.PackingStationTypePosition;
import com.mushiny.wms.masterdata.obbasics.exception.OutBoundException;
import com.mushiny.wms.masterdata.obbasics.repository.PackingStationPositionRepository;
import com.mushiny.wms.masterdata.obbasics.repository.PackingStationRepository;
import com.mushiny.wms.masterdata.obbasics.repository.PackingStationTypePositionRepository;
import com.mushiny.wms.masterdata.obbasics.repository.PackingStationTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PackingStationBusiness {

    private final ApplicationContext applicationContext;
    private final PackingStationRepository packingStationRepository;
    private final PackingStationPositionRepository packingStationPositionRepository;
    private final PackingStationTypeRepository packingStationTypeRepository;
    private final PackingStationTypePositionRepository packingStationTypePositionRepository;
    private final WorkStationRepository workStationRepository;
    private final WorkStationPositionRepository workStationPositionRepository;

    @Autowired
    public PackingStationBusiness(ApplicationContext applicationContext,
                                  PackingStationRepository packingStationRepository,
                                  PackingStationPositionRepository packingStationPositionRepository,
                                  PackingStationTypeRepository packingStationTypeRepository,
                                  PackingStationTypePositionRepository packingStationTypePositionRepository,
                                  WorkStationRepository workStationRepository,
                                  WorkStationPositionRepository workStationPositionRepository) {
        this.applicationContext = applicationContext;
        this.packingStationRepository = packingStationRepository;
        this.packingStationPositionRepository = packingStationPositionRepository;
        this.packingStationTypeRepository = packingStationTypeRepository;
        this.packingStationTypePositionRepository = packingStationTypePositionRepository;
        this.workStationRepository = workStationRepository;
        this.workStationPositionRepository = workStationPositionRepository;
    }

    public void createMore(PackingStationDTO dto) {
        PackingStationType packingStationType = packingStationTypeRepository.retrieve(dto.getTypeId());
        // 取出StationType下所有StationTypePosition
        List<PackingStationTypePosition> packingStationTypePositions = packingStationTypePositionRepository.getByStowStationType(packingStationType);

        PackingStation station = new PackingStation();
        station.setName(dto.getName());
        station.setDescription(dto.getDescription());
        station.setPackingStationType(packingStationTypeRepository.retrieve(dto.getTypeId()));
        station.setWorkStation(workStationRepository.retrieve(dto.getWorkstationId()));
        station.setWarehouseId(applicationContext.getCurrentWarehouse());
        station = packingStationRepository.save(station);
        for(PackingStationTypePosition StationTypePosition : packingStationTypePositions) {
            String positionState = StationTypePosition.getPositionState();
            int positionIndex = StationTypePosition.getPositionIndex();
            //用workStationId和PositionIndex取出从表的唯一Id
            WorkStation workStation = workStationRepository.retrieve(dto.getWorkstationId());
            WorkStationPosition workStationPosition = workStationPositionRepository.getByWorkStationIdAndPositionIndex(workStation,positionIndex);
            if(workStationPosition == null) {
                throw new ApiException(OutBoundException.EX_MD_OB_WORKSTATION_NOT_FOUND.toString(), positionIndex);
            }
            PackingStationPosition packingStationPosition = new PackingStationPosition();
            packingStationPosition.setWorkStationPosition(workStationPosition);
            packingStationPosition.setPositionState(positionState);
            packingStationPosition.setPackingStation(station);
            packingStationPosition.setWarehouseId(applicationContext.getCurrentWarehouse());
            packingStationPositionRepository.save(packingStationPosition);
        }
    }

}
