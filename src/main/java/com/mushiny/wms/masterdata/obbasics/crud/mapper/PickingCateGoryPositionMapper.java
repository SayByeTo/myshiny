package com.mushiny.wms.masterdata.obbasics.crud.mapper;

import com.mushiny.wms.common.context.ApplicationContext;
import com.mushiny.wms.common.crud.mapper.BaseMapper;
import com.mushiny.wms.masterdata.general.crud.mapper.WarehouseMapper;
import com.mushiny.wms.masterdata.general.repository.ClientRepository;
import com.mushiny.wms.masterdata.obbasics.crud.dto.PickingCateGoryPositionDTO;
import com.mushiny.wms.masterdata.obbasics.domain.PickingCateGoryPosition;
import com.mushiny.wms.masterdata.obbasics.repository.PickingCateGoryRepository;
import com.mushiny.wms.masterdata.obbasics.repository.PickingCateGoryRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PickingCateGoryPositionMapper implements BaseMapper<PickingCateGoryPositionDTO, PickingCateGoryPosition> {

    private final ApplicationContext applicationContext;
    private final WarehouseMapper warehouseMapper;
    private final PickingCateGoryMapper pickingCateGoryMapper;
    private final PickingCateGoryRepository pickingCateGoryRepository;
    private final PickingCateGoryRuleMapper pickingCateGoryRuleMapper;
    private final PickingCateGoryRuleRepository pickingCateGoryRuleRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public PickingCateGoryPositionMapper(ApplicationContext applicationContext,
                                         WarehouseMapper warehouseMapper,
                                         PickingCateGoryMapper pickingCateGoryMapper,
                                         PickingCateGoryRepository pickingCateGoryRepository,
                                         PickingCateGoryRuleMapper pickingCateGoryRuleMapper,
                                         PickingCateGoryRuleRepository pickingCateGoryRuleRepository,
                                         ClientRepository clientRepository) {
        this.applicationContext = applicationContext;
        this.warehouseMapper = warehouseMapper;
        this.pickingCateGoryMapper = pickingCateGoryMapper;
        this.pickingCateGoryRepository = pickingCateGoryRepository;
        this.pickingCateGoryRuleMapper = pickingCateGoryRuleMapper;
        this.pickingCateGoryRuleRepository = pickingCateGoryRuleRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public PickingCateGoryPositionDTO toDTO(PickingCateGoryPosition entity) {
        if (entity == null) {
            return null;
        }
        PickingCateGoryPositionDTO dto = new PickingCateGoryPositionDTO(entity);
        dto.setPositionNo(entity.getPositionNo());
        dto.setOrderIndex(entity.getOrderIndex());
        dto.setOperator(entity.getOperator());
        dto.setCompValue(entity.getCompValue());
        dto.setPickingCateGoryRule(pickingCateGoryRuleMapper.toDTO(entity.getPickingCateGoryRule()));
        dto.setPickingCateGory(pickingCateGoryMapper.toDTO(entity.getPickingCateGory()));
        dto.setClient(clientRepository.retrieve(entity.getClientId()).getName());
        dto.setClientId(entity.getClientId());
        dto.setWarehouseId(entity.getWarehouseId());
        return dto;
    }

    @Override
    public PickingCateGoryPosition toEntity(PickingCateGoryPositionDTO dto) {
        if (dto == null) {
            return null;
        }
        PickingCateGoryPosition entity = new PickingCateGoryPosition();

        entity.setId(dto.getId());
        entity.setAdditionalContent(dto.getAdditionalContent());
        entity.setPositionNo(dto.getPositionNo());
        entity.setOperator(dto.getOperator());
        entity.setOrderIndex(dto.getOrderIndex());
        entity.setCompValue(dto.getCompValue());
        if (dto.getPickingCateGoryRuleId() != null) {
            entity.setPickingCateGoryRule(pickingCateGoryRuleRepository.retrieve(dto.getPickingCateGoryRuleId()));
        }
        if (dto.getPickingCateGoryId() != null) {
            entity.setPickingCateGory(pickingCateGoryRepository.retrieve(dto.getPickingCateGoryId()));
        }
        applicationContext.isCurrentClient(dto.getClientId());
        entity.setClientId(dto.getClientId());
        entity.setWarehouseId(applicationContext.getCurrentWarehouse());
        return entity;
    }

    @Override
    public void updateEntityFromDTO(PickingCateGoryPositionDTO dto, PickingCateGoryPosition entity) {
    }
}
