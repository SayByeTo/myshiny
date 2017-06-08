package com.mushiny.wms.masterdata.mdbasics.crud.mapper;

import com.mushiny.wms.common.context.ApplicationContext;
import com.mushiny.wms.common.crud.mapper.BaseMapper;
import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.common.exception.ExceptionEnum;
import com.mushiny.wms.masterdata.mdbasics.crud.dto.WorkStationDTO;
import com.mushiny.wms.masterdata.mdbasics.domain.WorkStation;
import com.mushiny.wms.masterdata.mdbasics.repository.WorkStationTypeRepository;
import com.mushiny.wms.masterdata.obbasics.crud.mapper.PickPackWallMapper;
import com.mushiny.wms.masterdata.obbasics.repository.PickPackWallRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WorkStationMapper implements BaseMapper<WorkStationDTO, WorkStation> {

    private final ApplicationContext applicationContext;
    private final WorkStationTypeMapper workStationTypeMapper;
    private final WorkStationTypeRepository workStationTypeRepository;
    private final PickPackWallMapper pickPackWallMapper;
    private final PickPackWallRepository pickPackWallRepository;

    @Autowired
    public WorkStationMapper(ApplicationContext applicationContext,
                             WorkStationTypeMapper workStationTypeMapper,
                             WorkStationTypeRepository workStationTypeRepository,
                             PickPackWallMapper pickPackWallMapper,
                             PickPackWallRepository pickPackWallRepository) {
        this.applicationContext = applicationContext;
        this.workStationTypeMapper = workStationTypeMapper;
        this.workStationTypeRepository = workStationTypeRepository;
        this.pickPackWallMapper = pickPackWallMapper;
        this.pickPackWallRepository = pickPackWallRepository;
    }

    @Override
    public WorkStationDTO toDTO(WorkStation entity) {
        if (entity == null) {
            return null;
        }

        WorkStationDTO dto = new WorkStationDTO(entity);
        dto.setName(entity.getName());
        dto.setWorkstationType(workStationTypeMapper.toDTO(entity.getType()));
        dto.setFixedScanner(entity.getFixedScanner());
        dto.setPickPackWall(pickPackWallMapper.toDTO(entity.getPickPackWall()));

        dto.setWarehouseId(entity.getWarehouseId());

        return dto;
    }


    @Override
    public WorkStation toEntity(WorkStationDTO dto) {
        if (dto == null) {
            return null;
        }

        WorkStation entity = new WorkStation();

        entity.setId(dto.getId());
        entity.setAdditionalContent(dto.getAdditionalContent());
        entity.setName(dto.getName());
        entity.setType(workStationTypeRepository.retrieve(dto.getTypeId()));
        if(dto.getPickPackWallId().length() > 0) {
            entity.setPickPackWall(pickPackWallRepository.retrieve(dto.getPickPackWallId()));
        } else {
            entity.setPickPackWall(null);
        }
        entity.setFixedScanner(dto.getFixedScanner());

        entity.setWarehouseId(applicationContext.getCurrentWarehouse());
        return entity;
    }

    @Override
    public void updateEntityFromDTO(WorkStationDTO dto, WorkStation entity) {
        if (dto == null || entity == null) {
            throw new ApiException(ExceptionEnum.EX_SERVER_ERROR.toString());
        }
        entity.setId(dto.getId());
        entity.setAdditionalContent(dto.getAdditionalContent());
        entity.setName(dto.getName());
        entity.setType(workStationTypeRepository.retrieve(dto.getTypeId()));
        if(dto.getPickPackWallId().length() > 0) {
            entity.setPickPackWall(pickPackWallRepository.retrieve(dto.getPickPackWallId()));
        } else {
            entity.setPickPackWall(null);
        }
        entity.setFixedScanner(dto.getFixedScanner());

        entity.setWarehouseId(applicationContext.getCurrentWarehouse());
    }
}

