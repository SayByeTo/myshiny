package com.mushiny.wms.masterdata.obbasics.crud.mapper;

import com.mushiny.wms.common.crud.mapper.BaseMapper;
import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.common.exception.ExceptionEnum;
import com.mushiny.wms.common.context.ApplicationContext;
import com.mushiny.wms.masterdata.obbasics.crud.dto.ProcessPathDTO;
import com.mushiny.wms.masterdata.obbasics.domain.ProcessPath;
import com.mushiny.wms.masterdata.obbasics.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProcessPathMapper implements BaseMapper<ProcessPathDTO, ProcessPath> {

    private final ApplicationContext applicationContext;
    private final PickStationTypeRepository pickStationTypeRepository;
    private final ReBinStationTypeRepository reBinStationTypeRepository;
    private final ReBinWallTypeRepository reBinWallTypeRepository;
    private final PackingStationTypeRepository packingStationTypeRepository;
    private final ProcessPathTypeRepository processPathTypeRepository;

    private final PickStationTypeMapper pickStationTypeMapper;
    private final ReBinStationTypeMapper reBinStationTypeMapper;
    private final ReBinWallTypeMapper reBinWallTypeMapper;
    private final PackingStationTypeMapper packingStationTypeMapper;
    private final ProcessPathTypeMapper processPathTypeMapper;

    @Autowired
    public ProcessPathMapper(ReBinStationTypeMapper reBinStationTypeMapper,
                             ApplicationContext applicationContext,
                             PickStationTypeRepository pickStationTypeRepository,
                             ReBinStationTypeRepository reBinStationTypeRepository,
                             ReBinWallTypeRepository reBinWallTypeRepository,
                             PackingStationTypeRepository packingStationTypeRepository,
                             ProcessPathTypeRepository processPathTypeRepository,
                             PickStationTypeMapper pickStationTypeMapper,
                             ReBinWallTypeMapper reBinWallTypeMapper,
                             PackingStationTypeMapper packingStationTypeMapper,
                             ProcessPathTypeMapper processPathTypeMapper) {
        this.reBinStationTypeMapper = reBinStationTypeMapper;
        this.applicationContext = applicationContext;
        this.pickStationTypeRepository = pickStationTypeRepository;
        this.reBinStationTypeRepository = reBinStationTypeRepository;
        this.reBinWallTypeRepository = reBinWallTypeRepository;
        this.packingStationTypeRepository = packingStationTypeRepository;
        this.processPathTypeRepository = processPathTypeRepository;
        this.pickStationTypeMapper = pickStationTypeMapper;
        this.reBinWallTypeMapper = reBinWallTypeMapper;
        this.packingStationTypeMapper = packingStationTypeMapper;
        this.processPathTypeMapper = processPathTypeMapper;
    }

    @Override
    public ProcessPathDTO toDTO(ProcessPath entity) {
        if (entity == null) {
            return null;
        }
        ProcessPathDTO dto = new ProcessPathDTO(entity);

        dto.setName(entity.getName());
        dto.setSortCode(entity.getSortCode());
        dto.setDescription(entity.getDescription());
        dto.setRegenerateShortedPicks(entity.getRegenerateShortedPicks());
        dto.setMinShipmentsPerBatch(entity.getMinShipmentsPerBatch());
        dto.setMaxShipmentsPerBatch(entity.getMaxShipmentsPerBatch());
        dto.setMinItemsPerBatch(entity.getMinItemsPerBatch());
        dto.setMaxItemsPerBatch(entity.getMaxItemsPerBatch());
        dto.setCollateType(entity.getCollateType());
        dto.setCollateDocuments(entity.getCollateDocuments());
        dto.setToteBufferLimited(entity.getToteBufferLimited());
        dto.setRejectReasons(entity.getRejectReasons());
        dto.setAllowedPickTypes(entity.getAllowedPickTypes());
        dto.setAllowedContainerTypes(entity.getAllowedContainerTypes());
        dto.setLaborUnitClass(entity.getLaborUnitClass());
        dto.setWeightLimit(entity.getWeightLimit());
        dto.setScanBinFirst(entity.isScanBinFirst());
        dto.setColorBasedBinPicking(entity.isColorBasedBinPicking());
        dto.setColorBasedItemPicking(entity.isColorBasedItemPicking());
        dto.setOneItemPerTote(entity.isOneItemPerTote());
        dto.setRequireNewToteOnPickAreaChange(entity.isRequireNewToteOnPickAreaChange());
        dto.setIdleTimeoutInSecond(entity.getIdleTimeoutInSecond());
        dto.setToteRescan(entity.getToteRescan());
        dto.setPickToContainerImage(entity.getPickToContainerImage());
        dto.setTargetPickRate(entity.getTargetPickRate());
        dto.setProcessPad(entity.getProcessPad());
        dto.setToteLimit(entity.getToteLimit());
        dto.setBatchLimit(entity.getBatchLimit());

        dto.setWarehouseId(entity.getWarehouseId());
        dto.setPickDestination(pickStationTypeMapper.toDTO(entity.getPickStationType()));
        dto.setRebinDestination(reBinStationTypeMapper.toDTO(entity.getReBinDestination()));
        dto.setRebinWallType(reBinWallTypeMapper.toDTO(entity.getReBinWallType()));
        dto.setPackDestination(packingStationTypeMapper.toDTO(entity.getPackDestination()));
        dto.setProcessPathType(processPathTypeMapper.toDTO(entity.getProcessPathType()));

        return dto;
    }

    @Override
    public ProcessPath toEntity(ProcessPathDTO dto) {
        if (dto == null) {
            return null;
        }
        ProcessPath entity = new ProcessPath();

        entity.setId(dto.getId());
        entity.setAdditionalContent(dto.getAdditionalContent());
        entity.setName(dto.getName());
        entity.setSortCode(dto.getSortCode());
        entity.setDescription(dto.getDescription());
        entity.setRegenerateShortedPicks(dto.getRegenerateShortedPicks());
        entity.setMinShipmentsPerBatch(dto.getMinShipmentsPerBatch());
        entity.setMaxShipmentsPerBatch(dto.getMaxShipmentsPerBatch());
        entity.setMinItemsPerBatch(dto.getMinItemsPerBatch());
        entity.setMaxItemsPerBatch(dto.getMaxItemsPerBatch());
        entity.setCollateType(dto.getCollateType());
        entity.setCollateDocuments(dto.getCollateDocuments());
        entity.setToteBufferLimited(dto.getToteBufferLimited());
        entity.setRejectReasons(dto.getRejectReasons());
        entity.setAllowedPickTypes(dto.getAllowedPickTypes());
        entity.setAllowedContainerTypes(dto.getAllowedContainerTypes());
        entity.setLaborUnitClass(dto.getLaborUnitClass());
        entity.setWeightLimit(dto.getWeightLimit());
        entity.setScanBinFirst(dto.isScanBinFirst());
        entity.setColorBasedBinPicking(dto.isColorBasedBinPicking());
        entity.setColorBasedItemPicking(dto.isColorBasedItemPicking());
        entity.setOneItemPerTote(dto.isOneItemPerTote());
        entity.setRequireNewToteOnPickAreaChange(dto.isRequireNewToteOnPickAreaChange());
        entity.setIdleTimeoutInSecond(dto.getIdleTimeoutInSecond());
        entity.setToteRescan(dto.getToteRescan());
        entity.setPickToContainerImage(dto.getPickToContainerImage());
        entity.setTargetPickRate(dto.getTargetPickRate());
        entity.setProcessPad(dto.getProcessPad());
        entity.setToteLimit(dto.getToteLimit());
        entity.setBatchLimit(dto.getBatchLimit());

        entity.setWarehouseId(applicationContext.getCurrentWarehouse());
        if (dto.getPickDestinationId() != null) {
            entity.setPickStationType(pickStationTypeRepository.retrieve(dto.getPickDestinationId()));
        }
        if (dto.getRebinDestinationId() != null) {
            entity.setReBinDestination(reBinStationTypeRepository.retrieve(dto.getRebinDestinationId()));
        }
        if (dto.getRebinWallTypeId() != null) {
            entity.setReBinWallType(reBinWallTypeRepository.retrieve(dto.getRebinWallTypeId()));
        }
        if (dto.getPackDestinationId() != null) {
            entity.setPackDestination(packingStationTypeRepository.retrieve(dto.getPackDestinationId()));
        }
        if (dto.getProcessPathTypeId() != null) {
            entity.setProcessPathType(processPathTypeRepository.retrieve(dto.getProcessPathTypeId()));
        }

        return entity;
    }

    @Override
    public void updateEntityFromDTO(ProcessPathDTO dto, ProcessPath entity) {
        if (dto == null || entity == null) {
            throw new ApiException(ExceptionEnum.EX_SERVER_ERROR.toString());
        }

        entity.setEntityLock(dto.getEntityLock());
        entity.setAdditionalContent(dto.getAdditionalContent());
        entity.setName(dto.getName());
        entity.setSortCode(dto.getSortCode());
        entity.setDescription(dto.getDescription());
        entity.setRegenerateShortedPicks(dto.getRegenerateShortedPicks());
        entity.setMinShipmentsPerBatch(dto.getMinShipmentsPerBatch());
        entity.setMaxShipmentsPerBatch(dto.getMaxShipmentsPerBatch());
        entity.setMinItemsPerBatch(dto.getMinItemsPerBatch());
        entity.setMaxItemsPerBatch(dto.getMaxItemsPerBatch());
        entity.setCollateType(dto.getCollateType());
        entity.setCollateDocuments(dto.getCollateDocuments());
        entity.setToteBufferLimited(dto.getToteBufferLimited());
        entity.setRejectReasons(dto.getRejectReasons());
        entity.setAllowedPickTypes(dto.getAllowedPickTypes());
        entity.setAllowedContainerTypes(dto.getAllowedContainerTypes());
        entity.setLaborUnitClass(dto.getLaborUnitClass());
        entity.setWeightLimit(dto.getWeightLimit());
        entity.setScanBinFirst(dto.isScanBinFirst());
        entity.setColorBasedBinPicking(dto.isColorBasedBinPicking());
        entity.setColorBasedItemPicking(dto.isColorBasedItemPicking());
        entity.setOneItemPerTote(dto.isOneItemPerTote());
        entity.setRequireNewToteOnPickAreaChange(dto.isRequireNewToteOnPickAreaChange());
        entity.setIdleTimeoutInSecond(dto.getIdleTimeoutInSecond());
        entity.setToteRescan(dto.getToteRescan());
        entity.setPickToContainerImage(dto.getPickToContainerImage());
        entity.setTargetPickRate(dto.getTargetPickRate());
        entity.setProcessPad(dto.getProcessPad());
        entity.setToteLimit(dto.getToteLimit());
        entity.setBatchLimit(dto.getBatchLimit());

        entity.setWarehouseId(applicationContext.getCurrentWarehouse());
        if (dto.getPickDestinationId() != null) {
            entity.setPickStationType(pickStationTypeRepository.retrieve(dto.getPickDestinationId()));
        } else {
            entity.setPickStationType(null);
        }

        if (dto.getRebinDestinationId() != null) {
            entity.setReBinDestination(reBinStationTypeRepository.retrieve(dto.getRebinDestinationId()));
        } else {
            entity.setReBinDestination(null);
        }

        if (dto.getRebinWallTypeId() != null) {
            entity.setReBinWallType(reBinWallTypeRepository.retrieve(dto.getRebinWallTypeId()));
        } else {
            entity.setReBinWallType(null);
        }

        if (dto.getPackDestinationId() != null) {
            entity.setPackDestination(packingStationTypeRepository.retrieve(dto.getPackDestinationId()));
        } else {
            entity.setPackDestination(null);
        }

        if (dto.getProcessPathTypeId() != null) {
            entity.setProcessPathType(processPathTypeRepository.retrieve(dto.getProcessPathTypeId()));
        } else {
            entity.setProcessPathType(null);
        }
    }

}
