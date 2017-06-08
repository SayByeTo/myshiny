package com.mushiny.wms.masterdata.ibbasics.business;

import com.mushiny.wms.common.context.ApplicationContext;
import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.masterdata.general.repository.UserRepository;
import com.mushiny.wms.masterdata.ibbasics.crud.dto.StowStationDTO;
import com.mushiny.wms.masterdata.ibbasics.domain.StowStation;
import com.mushiny.wms.masterdata.ibbasics.domain.StowStationPosition;
import com.mushiny.wms.masterdata.ibbasics.domain.StowStationType;
import com.mushiny.wms.masterdata.ibbasics.domain.StowStationTypePosition;
import com.mushiny.wms.masterdata.ibbasics.exception.InBoundException;
import com.mushiny.wms.masterdata.ibbasics.repository.StowStationPositionRepository;
import com.mushiny.wms.masterdata.ibbasics.repository.StowStationRepository;
import com.mushiny.wms.masterdata.ibbasics.repository.StowStationTypePositionRepository;
import com.mushiny.wms.masterdata.ibbasics.repository.StowStationTypeRepository;
import com.mushiny.wms.masterdata.mdbasics.domain.WorkStation;
import com.mushiny.wms.masterdata.mdbasics.domain.WorkStationPosition;
import com.mushiny.wms.masterdata.mdbasics.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StowStationBusiness {

    private final ApplicationContext applicationContext;
    private final StowStationRepository stowStationRepository;
    private final StowStationPositionRepository stowStationPositionRepository;
    private final StowStationTypeRepository stowStationTypeRepository;
    private final StowStationTypePositionRepository stowStationTypePositionRepository;
    private final WorkStationRepository workStationRepository;
    private final WorkStationPositionRepository workStationPositionRepository;
    private final UserRepository userRepository;

    @Autowired
    public StowStationBusiness(ApplicationContext applicationContext,
                               StowStationRepository stowStationRepository,
                               StowStationPositionRepository stowStationPositionRepository,
                               StowStationTypeRepository stowStationTypeRepository,
                               StowStationTypePositionRepository stowStationTypePositionRepository,
                               WorkStationRepository workStationRepository,
                               WorkStationPositionRepository workStationPositionRepository,
                               UserRepository userRepository) {
        this.applicationContext = applicationContext;
        this.stowStationRepository = stowStationRepository;
        this.stowStationPositionRepository = stowStationPositionRepository;
        this.stowStationTypeRepository = stowStationTypeRepository;
        this.stowStationTypePositionRepository = stowStationTypePositionRepository;
        this.workStationRepository = workStationRepository;
        this.workStationPositionRepository = workStationPositionRepository;
        this.userRepository = userRepository;
    }

    public void createMore(StowStationDTO dto) {
        StowStationType stowStationType = stowStationTypeRepository.retrieve(dto.getTypeId());
        // 取出StowStationType下所有StowStationTypePosition
        List<StowStationTypePosition> stowStationTypePositions = stowStationTypePositionRepository.getByStowStationType(stowStationType);

        StowStation station = new StowStation();
        station.setName(dto.getName());
        station.setDescription(dto.getDescription());
        station.setType(stowStationTypeRepository.retrieve(dto.getTypeId()));
        if(dto.getOperatorId() != null) {
            station.setOperator(userRepository.retrieve(dto.getOperatorId()));
        } else {
            station.setOperator(null);
        }
        station.setWorkstation(workStationRepository.retrieve(dto.getWorkstationId()));
        station.setWarehouseId(applicationContext.getCurrentWarehouse());
        station = stowStationRepository.save(station);
        for(StowStationTypePosition StationTypePosition : stowStationTypePositions) {
            String positionState = StationTypePosition.getPositionState();
            int positionIndex = StationTypePosition.getPositionIndex();
            //用workStationId和PositionIndex取出从表的唯一Id
            WorkStation workStation = workStationRepository.retrieve(dto.getWorkstationId());
            WorkStationPosition workStationPosition = workStationPositionRepository.getByWorkStationIdAndPositionIndex(workStation,positionIndex);
            if(workStationPosition == null) {
                throw new ApiException(InBoundException.EX_MD_IN_WORKSTATION_NOT_FOUND.toString(), positionIndex);
            }
            StowStationPosition stowStationPosition = new StowStationPosition();
            stowStationPosition.setWorkStationPosition(workStationPosition);
            stowStationPosition.setPositionState(positionState);
            stowStationPosition.setStowStation(station);
            stowStationPosition.setWarehouseId(applicationContext.getCurrentWarehouse());
            stowStationPositionRepository.save(stowStationPosition);
        }
    }

}
