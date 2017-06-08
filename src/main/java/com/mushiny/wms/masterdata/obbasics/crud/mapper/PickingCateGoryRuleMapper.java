package com.mushiny.wms.masterdata.obbasics.crud.mapper;

import com.mushiny.wms.common.context.ApplicationContext;
import com.mushiny.wms.common.crud.mapper.BaseMapper;
import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.common.exception.ExceptionEnum;
import com.mushiny.wms.masterdata.general.crud.mapper.WarehouseMapper;
import com.mushiny.wms.masterdata.obbasics.crud.dto.PackingStationDTO;
import com.mushiny.wms.masterdata.obbasics.crud.dto.PickingCateGoryRuleDTO;
import com.mushiny.wms.masterdata.obbasics.domain.PackingStation;
import com.mushiny.wms.masterdata.obbasics.domain.PickingCateGoryRule;
import com.mushiny.wms.masterdata.obbasics.repository.PackingStationTypeRepository;
import org.springframework.stereotype.Component;

@Component
public class PickingCateGoryRuleMapper implements BaseMapper<PickingCateGoryRuleDTO, PickingCateGoryRule> {

    private final PackingStationTypeRepository packingStationTypeRepository;
    private final PackingStationTypeMapper packingStationTypeMapper;
    private final ApplicationContext applicationContext;
    private final WarehouseMapper warehouseMapper;

    public PickingCateGoryRuleMapper(PackingStationTypeRepository packingStationTypeRepository,
                                     PackingStationTypeMapper packingStationTypeMapper,
                                     ApplicationContext applicationContext,
                                     WarehouseMapper warehouseMapper) {
        this.packingStationTypeRepository = packingStationTypeRepository;
        this.packingStationTypeMapper = packingStationTypeMapper;
        this.applicationContext = applicationContext;
        this.warehouseMapper = warehouseMapper;
    }

    @Override
    public PickingCateGoryRuleDTO toDTO(PickingCateGoryRule entity) {
        if (entity == null) {
            return null;
        }
        PickingCateGoryRuleDTO dto = new PickingCateGoryRuleDTO(entity);

        dto.setRuleKey(entity.getRuleKey());
        dto.setName(entity.getName());
        dto.setComparisonType(entity.getComparisonType());

        return dto;
    }

    @Override
    public PickingCateGoryRule toEntity(PickingCateGoryRuleDTO dto) {
        if (dto == null) {
            return null;
        }
        PickingCateGoryRule entity = new PickingCateGoryRule();

        entity.setId(dto.getId());
        entity.setAdditionalContent(dto.getAdditionalContent());
        entity.setName(dto.getName());
        entity.setRuleKey(dto.getRuleKey());
        entity.setComparisonType(dto.getComparisonType());

        return entity;
    }

    @Override
    public void updateEntityFromDTO(PickingCateGoryRuleDTO dto, PickingCateGoryRule entity) {
        if (dto == null || entity == null) {
            throw new ApiException(ExceptionEnum.EX_SERVER_ERROR.toString());
        }

        entity.setAdditionalContent(dto.getAdditionalContent());
        entity.setName(dto.getName());
        entity.setRuleKey(dto.getRuleKey());
        entity.setComparisonType(dto.getComparisonType());


    }
}
