package com.mushiny.wms.masterdata.obbasics.service.impl;

import com.mushiny.wms.common.context.ApplicationContext;
import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.masterdata.obbasics.crud.dto.PickPackWallTypeDTO;
import com.mushiny.wms.masterdata.obbasics.crud.dto.PickPackWallTypePositionDTO;
import com.mushiny.wms.masterdata.obbasics.crud.mapper.PickPackFieldTypeMapper;
import com.mushiny.wms.masterdata.obbasics.crud.mapper.PickPackWallTypeMapper;
import com.mushiny.wms.masterdata.obbasics.crud.mapper.PickPackWallTypePositionMapper;
import com.mushiny.wms.masterdata.obbasics.domain.PickPackFieldType;
import com.mushiny.wms.masterdata.obbasics.domain.PickPackWallType;
import com.mushiny.wms.masterdata.obbasics.domain.PickPackWallTypePosition;
import com.mushiny.wms.masterdata.obbasics.exception.OutBoundException;
import com.mushiny.wms.masterdata.obbasics.repository.PickPackFieldTypeRepository;
import com.mushiny.wms.masterdata.obbasics.repository.PickPackWallTypePositionRepository;
import com.mushiny.wms.masterdata.obbasics.repository.PickPackWallTypeRepository;
import com.mushiny.wms.masterdata.obbasics.service.PickPackWallTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PickPackWallTypeServiceImpl implements PickPackWallTypeService {

    private final PickPackWallTypeRepository pickPackWallTypeRepository;
    private final ApplicationContext applicationContext;
    private final PickPackWallTypeMapper pickPackWallTypeMapper;
    private final PickPackFieldTypeMapper pickPackFieldTypeMapper;
    private final PickPackFieldTypeRepository pickPackFieldTypeRepository;
    private final PickPackWallTypePositionMapper pickPackWallTypePositionMapper;
    private final PickPackWallTypePositionRepository pickPackWallTypePositionRepository;

    @Autowired
    public PickPackWallTypeServiceImpl(PickPackWallTypeRepository pickPackWallTypeRepository,
                                       ApplicationContext applicationContext,
                                       PickPackWallTypeMapper pickPackWallTypeMapper,
                                       PickPackFieldTypeMapper pickPackFieldTypeMapper,
                                       PickPackFieldTypeRepository pickPackFieldTypeRepository,
                                       PickPackWallTypePositionMapper pickPackWallTypePositionMapper,
                                       PickPackWallTypePositionRepository pickPackWallTypePositionRepository) {
        this.pickPackWallTypeRepository = pickPackWallTypeRepository;
        this.applicationContext = applicationContext;
        this.pickPackWallTypeMapper = pickPackWallTypeMapper;
        this.pickPackFieldTypeMapper = pickPackFieldTypeMapper;
        this.pickPackFieldTypeRepository = pickPackFieldTypeRepository;
        this.pickPackWallTypePositionMapper = pickPackWallTypePositionMapper;
        this.pickPackWallTypePositionRepository = pickPackWallTypePositionRepository;
    }

    @Override
    public PickPackWallTypeDTO create(PickPackWallTypeDTO dto) {
        PickPackWallType entity = pickPackWallTypeMapper.toEntity(dto);
        checkName(entity.getWarehouseId(), entity.getName());
        for (PickPackWallTypePositionDTO positionDTO : dto.getPositions()) {
            entity.addPosition(pickPackWallTypePositionMapper.toEntity(positionDTO));
        }
        return pickPackWallTypeMapper.toDTO(pickPackWallTypeRepository.save(entity));
    }

    @Override
    public void delete(String id) {
        PickPackWallType entity = pickPackWallTypeRepository.retrieve(id);
        pickPackWallTypeRepository.delete(entity);
    }

    @Override
    public PickPackWallTypeDTO update(PickPackWallTypeDTO dto) {
        PickPackWallType entity = pickPackWallTypeRepository.retrieve(dto.getId());
        if (!(entity.getName().equalsIgnoreCase(dto.getName()))) {
            checkName(entity.getWarehouseId(), dto.getName());
        }
        pickPackWallTypeMapper.updateEntityFromDTO(dto, entity);
        entity.getPositions().clear();
        List<PickPackWallTypePosition> positions = pickPackWallTypePositionMapper.toEntityList(dto.getPositions());
        for (PickPackWallTypePosition position : positions) {
            entity.addPosition(position);
        }

        return pickPackWallTypeMapper.toDTO(pickPackWallTypeRepository.save(entity));
    }

    @Override
    public PickPackWallTypeDTO retrieve(String id) {
        PickPackWallType entity = pickPackWallTypeRepository.retrieve(id);
        PickPackWallTypeDTO dto = pickPackWallTypeMapper.toDTO(entity);
        dto.setPositions(pickPackWallTypePositionMapper.toDTOList(entity.getPositions()));
        List<PickPackFieldType> pickPackFieldTypes = pickPackWallTypePositionRepository.getByTypeId(id);
        dto.setPickPackFieldTypes(pickPackFieldTypeMapper.toDTOList(pickPackFieldTypes));
        return dto;
    }

    @Override
    public List<PickPackWallTypeDTO> getAll() {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "name"));
        List<PickPackWallType> entities = pickPackWallTypeRepository.getList(null, sort);
        return pickPackWallTypeMapper.toDTOList(entities);
    }

    @Override
    public List<PickPackWallTypeDTO> getBySearchTerm(String searchTerm, Sort sort) {
        List<PickPackWallType> entities = pickPackWallTypeRepository.getBySearchTerm(searchTerm, sort);
        return pickPackWallTypeMapper.toDTOList(entities);
    }

    @Override
    public Page<PickPackWallTypeDTO> getBySearchTerm(String searchTerm, Pageable pageable) {
        Page<PickPackWallType> entities = pickPackWallTypeRepository.getBySearchTerm(searchTerm, pageable);
        Page<PickPackWallTypeDTO> dtoPage = pickPackWallTypeMapper.toDTOPage(pageable, entities);
        if (dtoPage.getContent() != null) {
            for (PickPackWallTypeDTO dto : dtoPage.getContent()) {
                List<PickPackFieldType> pickPackFieldTypes = pickPackWallTypePositionRepository.getByTypeId(dto.getId());
                dto.setPickPackFieldTypes(pickPackFieldTypeMapper.toDTOList(pickPackFieldTypes));
            }
        }
        return dtoPage;
    }

    private void checkName(String warehouse, String areaName) {
        PickPackWallType workStationType = pickPackWallTypeRepository.getByName(warehouse, areaName);
        if (workStationType != null) {
            throw new ApiException(OutBoundException.EX_MD_OB_PICKPACK_WALL_TYPE_NAME_UNIQUE.toString(), areaName);
        }
    }
}