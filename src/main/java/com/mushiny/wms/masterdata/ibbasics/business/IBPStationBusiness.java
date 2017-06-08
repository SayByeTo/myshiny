package com.mushiny.wms.masterdata.ibbasics.business;

import com.mushiny.wms.common.context.ApplicationContext;
import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.masterdata.general.repository.UserRepository;
import com.mushiny.wms.masterdata.mdbasics.domain.WorkStation;
import com.mushiny.wms.masterdata.mdbasics.domain.WorkStationPosition;
import com.mushiny.wms.masterdata.mdbasics.repository.WorkStationPositionRepository;
import com.mushiny.wms.masterdata.mdbasics.repository.WorkStationRepository;
import com.mushiny.wms.masterdata.ibbasics.crud.dto.IBPStationDTO;
import com.mushiny.wms.masterdata.ibbasics.domain.IBPStation;
import com.mushiny.wms.masterdata.ibbasics.domain.IBPStationPosition;
import com.mushiny.wms.masterdata.ibbasics.domain.IBPStationType;
import com.mushiny.wms.masterdata.ibbasics.domain.IBPStationTypePosition;
import com.mushiny.wms.masterdata.ibbasics.repository.IBPStationPositionRepository;
import com.mushiny.wms.masterdata.ibbasics.repository.IBPStationRepository;
import com.mushiny.wms.masterdata.ibbasics.repository.IBPStationTypePositionRepository;
import com.mushiny.wms.masterdata.ibbasics.repository.IBPStationTypeRepository;
import com.mushiny.wms.masterdata.ibbasics.exception.InBoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class IBPStationBusiness {

    private final ApplicationContext applicationContext;
    private final IBPStationRepository ibpStationRepository;
    private final IBPStationPositionRepository ibpStationPositionRepository;
    private final IBPStationTypeRepository ibpStationTypeRepository;
    private final IBPStationTypePositionRepository ibpStationTypePositionRepository;
    private final WorkStationRepository workStationRepository;
    private final WorkStationPositionRepository workStationPositionRepository;
    private final UserRepository userRepository;

    @Autowired
    public IBPStationBusiness(ApplicationContext applicationContext,
                              IBPStationRepository ibpStationRepository,
                              IBPStationPositionRepository ibpStationPositionRepository,
                              IBPStationTypeRepository ibpStationTypeRepository,
                              IBPStationTypePositionRepository ibpStationTypePositionRepository,
                              WorkStationRepository workStationRepository,
                              WorkStationPositionRepository workStationPositionRepository,
                              UserRepository userRepository) {
        this.applicationContext = applicationContext;
        this.ibpStationRepository = ibpStationRepository;
        this.ibpStationPositionRepository = ibpStationPositionRepository;
        this.ibpStationTypeRepository = ibpStationTypeRepository;
        this.ibpStationTypePositionRepository = ibpStationTypePositionRepository;

        this.workStationRepository = workStationRepository;
        this.workStationPositionRepository = workStationPositionRepository;
        this.userRepository = userRepository;
    }

    public void createMore(IBPStationDTO dto) {
        IBPStationType ibpStationType = ibpStationTypeRepository.retrieve(dto.getTypeId());
        // 取出StationType下所有StationTypePosition
        List<IBPStationTypePosition> ibpStationTypePositions = ibpStationTypePositionRepository.getByIBPStationType(ibpStationType);

        IBPStation station = new IBPStation();
        station.setName(dto.getName());
        station.setDescription(dto.getDescription());
        station.setIbpStationType(ibpStationTypeRepository.retrieve(dto.getTypeId()));
        if(dto.getOperatorId() != null) {
            station.setOperatorId(userRepository.retrieve(dto.getOperatorId()));
        } else {
            station.setOperatorId(null);
        }
        station.setWorkStation(workStationRepository.retrieve(dto.getWorkstationId()));
        station.setWarehouseId(applicationContext.getCurrentWarehouse());
        station = ibpStationRepository.save(station);
        for(IBPStationTypePosition StationTypePosition : ibpStationTypePositions) {
            String positionState = StationTypePosition.getPositionState();
            int positionIndex = StationTypePosition.getPositionIndex();
            //用workStationId和PositionIndex取出从表的唯一Id
            WorkStation workStation = workStationRepository.retrieve(dto.getWorkstationId());
            WorkStationPosition workStationPosition = workStationPositionRepository.getByWorkStationIdAndPositionIndex(workStation,positionIndex);
            if(workStationPosition == null) {
                throw new ApiException(InBoundException.EX_MD_IN_WORKSTATION_NOT_FOUND.toString(), positionIndex);
            }
            IBPStationPosition ibpStationPosition = new IBPStationPosition();
            ibpStationPosition.setWorkStationPosition(workStationPosition);
            ibpStationPosition.setPositionState(positionState);
            ibpStationPosition.setIbpStation(station);
            ibpStationPosition.setWarehouseId(applicationContext.getCurrentWarehouse());
            ibpStationPositionRepository.save(ibpStationPosition);
        }
    }

}
