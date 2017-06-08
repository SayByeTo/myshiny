package com.mushiny.wms.masterdata.obbasics.crud.mapper;

import com.mushiny.wms.common.context.ApplicationContext;
import com.mushiny.wms.common.crud.mapper.BaseMapper;
import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.common.exception.ExceptionEnum;
import com.mushiny.wms.masterdata.general.crud.mapper.WarehouseMapper;
import com.mushiny.wms.masterdata.general.repository.ClientRepository;
import com.mushiny.wms.masterdata.obbasics.crud.dto.PickingCateGoryDTO;
import com.mushiny.wms.masterdata.obbasics.domain.PickingCateGory;
import com.mushiny.wms.masterdata.obbasics.repository.ProcessPathRepository;
import org.springframework.stereotype.Component;

@Component
public class PickingCateGoryMapper implements BaseMapper<PickingCateGoryDTO, PickingCateGory> {

    private final ApplicationContext applicationContext;
    private final WarehouseMapper warehouseMapper;
    private final ProcessPathMapper processPathMapper;
    private final ProcessPathRepository processPathRepository;
    private final ClientRepository clientRepository;

    public PickingCateGoryMapper(ApplicationContext applicationContext,
                                 WarehouseMapper warehouseMapper,
                                 ProcessPathMapper processPathMapper,
                                 ProcessPathRepository processPathRepository,
                                 ClientRepository clientRepository) {
        this.applicationContext = applicationContext;
        this.warehouseMapper = warehouseMapper;
        this.processPathMapper = processPathMapper;
        this.processPathRepository = processPathRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public PickingCateGoryDTO toDTO(PickingCateGory entity) {
        if (entity == null) {
            return null;
        }
        PickingCateGoryDTO dto = new PickingCateGoryDTO(entity);

        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setCateGoryType(entity.getCateGoryType());
        dto.setConsumerType(entity.getConsumerType());
        dto.setOrderIndex(entity.getOrderIndex());
        dto.setProcessPath(processPathMapper.toDTO(entity.getProcessPath()));
        dto.setClient(clientRepository.retrieve(entity.getClientId()).getName());
        dto.setClientId(entity.getClientId());
        dto.setWarehouseId(entity.getWarehouseId());
        return dto;
    }

    @Override
    public PickingCateGory toEntity(PickingCateGoryDTO dto) {
        if (dto == null) {
            return null;
        }
        PickingCateGory entity = new PickingCateGory();

        entity.setId(dto.getId());
        entity.setAdditionalContent(dto.getAdditionalContent());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setConsumerType(dto.getConsumerType());
        entity.setCateGoryType(dto.getCateGoryType());
        entity.setOrderIndex(dto.getOrderIndex());
        if(dto.getProcessPathId() != null) {
            entity.setProcessPath(processPathRepository.retrieve(dto.getProcessPathId()));
        }
        applicationContext.isCurrentClient(dto.getClientId());
        entity.setClientId(dto.getClientId());
        entity.setWarehouseId(applicationContext.getCurrentWarehouse());
        return entity;
    }

    @Override
    public void updateEntityFromDTO(PickingCateGoryDTO dto, PickingCateGory entity) {
        if (dto == null || entity == null) {
            throw new ApiException(ExceptionEnum.EX_SERVER_ERROR.toString());
        }

        entity.setAdditionalContent(dto.getAdditionalContent());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setConsumerType(dto.getConsumerType());
        entity.setCateGoryType(dto.getCateGoryType());
        entity.setOrderIndex(dto.getOrderIndex());
        if(dto.getProcessPathId() != null) {
            entity.setProcessPath(processPathRepository.retrieve(dto.getProcessPathId()));
        }
        applicationContext.isCurrentClient(dto.getClientId());
        entity.setClientId(dto.getClientId());
    }
}
