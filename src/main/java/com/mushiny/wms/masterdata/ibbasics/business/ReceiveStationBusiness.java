package com.mushiny.wms.masterdata.ibbasics.business;

import com.mushiny.wms.common.context.ApplicationContext;
import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.masterdata.general.repository.UserRepository;
import com.mushiny.wms.masterdata.ibbasics.crud.dto.ReceiveStationDTO;
import com.mushiny.wms.masterdata.ibbasics.domain.*;
import com.mushiny.wms.masterdata.ibbasics.repository.*;
import com.mushiny.wms.masterdata.mdbasics.domain.WorkStation;
import com.mushiny.wms.masterdata.mdbasics.domain.WorkStationPosition;
import com.mushiny.wms.masterdata.mdbasics.repository.WorkStationPositionRepository;
import com.mushiny.wms.masterdata.mdbasics.repository.WorkStationRepository;
import com.mushiny.wms.masterdata.ibbasics.exception.InBoundException;
import javassist.expr.NewArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReceiveStationBusiness {

    private final ApplicationContext applicationContext;

    private final ReceiveStationRepository receiveStationRepository;
    private final ReceiveStationPositionRepository receiveStationPositionRepository;
    private final ReceiveStationTypeRepository receiveStationTypeRepository;
    private final ReceiveStationTypePositionRepository receiveStationTypePositionRepository;
    private final WorkStationRepository workStationRepository;
    private final WorkStationPositionRepository workStationPositionRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReceiveStationBusiness(ApplicationContext applicationContext,
                                  ReceiveStationRepository receiveStationRepository,
                                  ReceiveStationPositionRepository receiveStationPositionRepository,
                                  ReceiveStationTypeRepository receiveStationTypeRepository,
                                  ReceiveStationTypePositionRepository receiveStationTypePositionRepository,
                                  WorkStationRepository workStationRepository,
                                  WorkStationPositionRepository workStationPositionRepository,
                                  UserRepository userRepository) {
        this.applicationContext = applicationContext;
        this.receiveStationRepository = receiveStationRepository;
        this.receiveStationPositionRepository = receiveStationPositionRepository;
        this.receiveStationTypeRepository = receiveStationTypeRepository;
        this.receiveStationTypePositionRepository = receiveStationTypePositionRepository;
        this.workStationRepository = workStationRepository;
        this.workStationPositionRepository = workStationPositionRepository;
        this.userRepository = userRepository;
    }

    public void createMore(ReceiveStationDTO dto) {
        ReceiveStationType receiveStationType = receiveStationTypeRepository.retrieve(dto.getTypeId());
        // 取出StowStationType下所有StowStationTypePosition
        List<ReceiveStationTypePosition> receiveStationTypePositions = receiveStationTypePositionRepository.getByStowStationType(receiveStationType);

        ReceiveStation station = new ReceiveStation();
        station.setName(dto.getName());
        station.setDescription(dto.getDescription());
        station.setReceivingStationType(receiveStationTypeRepository.retrieve(dto.getTypeId()));
        station.setOperator(dto.getOperatorId());
        station.setWorkStation(workStationRepository.retrieve(dto.getWorkstationId()));
        station.setWarehouseId(applicationContext.getCurrentWarehouse());
        station = receiveStationRepository.save(station);
        for(ReceiveStationTypePosition StationTypePosition : receiveStationTypePositions) {
            String positionState = StationTypePosition.getPositionState();
            int positionIndex = StationTypePosition.getPositionIndex();

            //用workStationId和PositionIndex取出从表的唯一Id
            WorkStation workStation = workStationRepository.retrieve(dto.getWorkstationId());
            WorkStationPosition workStationPosition = workStationPositionRepository.getByWorkStationIdAndPositionIndex(workStation,positionIndex);
            if(workStationPosition == null) {
                throw new ApiException(InBoundException.EX_MD_IN_WORKSTATION_NOT_FOUND.toString(), positionIndex);
            }
            ReceiveStationPosition receiveStationPosition = new ReceiveStationPosition();
            receiveStationPosition.setWorkStationPosition(workStationPosition);
            receiveStationPosition.setPositionState(positionState);
            receiveStationPosition.setReceiveStation(station);
            receiveStationPosition.setWarehouseId(applicationContext.getCurrentWarehouse());
            receiveStationPositionRepository.save(receiveStationPosition);
        }

    }

}
