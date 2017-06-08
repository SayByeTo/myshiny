package com.mushiny.wms.masterdata.mdbasics.crud.mapper;

import com.mushiny.wms.common.context.ApplicationContext;
import com.mushiny.wms.common.crud.mapper.BaseMapper;
import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.common.exception.ExceptionEnum;
import com.mushiny.wms.masterdata.mdbasics.crud.dto.StorageLocationDTO;
import com.mushiny.wms.masterdata.mdbasics.domain.StorageLocation;
import com.mushiny.wms.masterdata.mdbasics.repository.*;
import com.mushiny.wms.masterdata.general.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StorageLocationMapper implements BaseMapper<StorageLocationDTO, StorageLocation> {

    private final StorageLocationTypeRepository storageLocationTypeRepository;
    private final AreaRepository areaRepository;
    private final ZoneRepository zoneRepository;
    private final DropZoneRepository dropZoneRepository;
    private final PodRepository bayRepository;
    private final ApplicationContext applicationContext;

    private final StorageLocationTypeMapper storageLocationTypeMapper;
    private final AreaMapper areaMapper;
    private final ZoneMapper zoneMapper;
    private final DropZoneMapper dropZoneMapper;
    private final PodMapper podMapper;
    private final ClientRepository clientRepository;

    @Autowired
    public StorageLocationMapper(AreaRepository areaRepository,
                                 StorageLocationTypeRepository storageLocationTypeRepository,
                                 ApplicationContext applicationContext,
                                 ZoneRepository zoneRepository,
                                 DropZoneRepository dropZoneRepository,
                                 PodRepository bayRepository,
                                 AreaMapper areaMapper,
                                 ZoneMapper zoneMapper,
                                 StorageLocationTypeMapper storageLocationTypeMapper,
                                 DropZoneMapper dropZoneMapper,
                                 PodMapper podMapper,
                                 ClientRepository clientRepository) {
        this.areaRepository = areaRepository;
        this.storageLocationTypeRepository = storageLocationTypeRepository;
        this.applicationContext = applicationContext;
        this.zoneRepository = zoneRepository;
        this.dropZoneRepository = dropZoneRepository;
        this.bayRepository = bayRepository;

        this.areaMapper = areaMapper;
        this.zoneMapper = zoneMapper;
        this.storageLocationTypeMapper = storageLocationTypeMapper;
        this.dropZoneMapper = dropZoneMapper;
        this.podMapper = podMapper;
        this.clientRepository = clientRepository;
    }

    @Override
    public StorageLocationDTO toDTO(StorageLocation entity) {
        if (entity == null) {
            return null;
        }

        StorageLocationDTO dto = new StorageLocationDTO(entity);

        dto.setName(entity.getName());
        dto.setxPos(entity.getxPos());
        dto.setyPos(entity.getyPos());
        dto.setzPos(entity.getzPos());
        dto.setField(entity.getField());
        dto.setFieldIndex(entity.getFieldIndex());
        dto.setOrderIndex(entity.getOrderIndex());
        dto.setAllocation(entity.getAllocation());
        dto.setAllocationState(entity.getAllocationState());
        dto.setStocktakingDate(entity.getStocktakingDate());
        dto.setFace(entity.getFace());
        dto.setColor(entity.getColor());

        dto.setStorageLocationType(storageLocationTypeMapper.toDTO(entity.getStorageLocationType()));
        dto.setArea(areaMapper.toDTO(entity.getArea()));
        dto.setZone(zoneMapper.toDTO(entity.getZone()));
        dto.setDropZone(dropZoneMapper.toDTO(entity.getDropZone()));
        dto.setPod(podMapper.toDTO(entity.getPod()));
//        dto.setClient(clientRepository.retrieve(entity.getClientId()).getName());
//        dto.setClientId(entity.getClientId());
        dto.setWarehouseId(entity.getWarehouseId());

        return dto;
    }

    @Override
    public StorageLocation toEntity(StorageLocationDTO dto) {
        if (dto == null) {
            return null;
        }

        StorageLocation entity = new StorageLocation();

        entity.setId(dto.getId());
        entity.setAdditionalContent(dto.getAdditionalContent());
        entity.setName(dto.getName());
        entity.setxPos(dto.getxPos());
        entity.setyPos(dto.getyPos());
        entity.setzPos(dto.getzPos());
        entity.setField(dto.getField());
        entity.setFieldIndex(dto.getFieldIndex());
        entity.setOrderIndex(dto.getOrderIndex());
        entity.setAllocation(dto.getAllocation());
        entity.setAllocationState(dto.getAllocationState());
        entity.setStocktakingDate(dto.getStocktakingDate());
        entity.setFace(dto.getFace());
        entity.setColor(dto.getColor());

        applicationContext.isCurrentClient(dto.getClientId());
        entity.setClientId(dto.getClientId());
        entity.setWarehouseId(applicationContext.getCurrentWarehouse());
        if (dto.getTypeId() != null) {
            entity.setStorageLocationType(storageLocationTypeRepository.retrieve(dto.getTypeId()));
        }
        if (dto.getAreaId() != null) {
            entity.setArea(areaRepository.retrieve(dto.getAreaId()));
        }
        if (dto.getZoneId() != null) {
            entity.setZone(zoneRepository.retrieve(dto.getZoneId()));
        }
        if (dto.getDropZoneId() != null) {
            entity.setDropZone(dropZoneRepository.retrieve(dto.getDropZoneId()));
        }
        if (dto.getPodId() != null) {
            entity.setPod(bayRepository.retrieve(dto.getPodId()));
        }

        return entity;
    }

    @Override
    public void updateEntityFromDTO(StorageLocationDTO dto, StorageLocation entity) {
        if (dto == null || entity == null) {
            throw new ApiException(ExceptionEnum.EX_SERVER_ERROR.toString());
        }
        entity.setId(dto.getId());
        entity.setAdditionalContent(dto.getAdditionalContent());
        entity.setField(dto.getField());
        entity.setFieldIndex(dto.getFieldIndex());
        entity.setOrderIndex(dto.getOrderIndex());
        entity.setAllocation(dto.getAllocation());
        entity.setAllocationState(dto.getAllocationState());
        entity.setStocktakingDate(dto.getStocktakingDate());
        entity.setColor(dto.getColor());

        applicationContext.isCurrentClient(dto.getClientId());
        entity.setClientId(dto.getClientId());
        if (dto.getAreaId() != null) {
            entity.setArea(areaRepository.retrieve(dto.getAreaId()));
        } else {
            entity.setArea(null);
        }

        if (dto.getDropZoneId() != null) {
            entity.setDropZone(dropZoneRepository.retrieve(dto.getDropZoneId()));
        } else {
            entity.setDropZone(null);
        }
    }
}

