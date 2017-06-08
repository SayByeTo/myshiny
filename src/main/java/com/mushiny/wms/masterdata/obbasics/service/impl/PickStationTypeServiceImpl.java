package com.mushiny.wms.masterdata.obbasics.service.impl;

import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.masterdata.obbasics.crud.dto.PackingStationTypePositionDTO;
import com.mushiny.wms.masterdata.obbasics.crud.dto.PickStationTypeDTO;
import com.mushiny.wms.masterdata.obbasics.crud.dto.PickStationTypePositionDTO;
import com.mushiny.wms.masterdata.obbasics.crud.mapper.PickStationTypeMapper;
import com.mushiny.wms.masterdata.obbasics.crud.mapper.PickStationTypePositionMapper;
import com.mushiny.wms.masterdata.obbasics.domain.PickStationType;
import com.mushiny.wms.masterdata.obbasics.domain.PickStationTypePosition;
import com.mushiny.wms.masterdata.obbasics.exception.OutBoundException;
import com.mushiny.wms.masterdata.obbasics.repository.PackingStationRepository;
import com.mushiny.wms.masterdata.obbasics.repository.PackingStationTypeRepository;
import com.mushiny.wms.masterdata.obbasics.repository.PickStationTypeRepository;
import com.mushiny.wms.masterdata.obbasics.service.PickStationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PickStationTypeServiceImpl implements PickStationTypeService {

    private final PickStationTypeRepository pickStationTypeRepository;
    private final PackingStationRepository packingStationRepository;
    private final PickStationTypeMapper pickStationTypeMapper;
    private final PickStationTypePositionMapper pickStationTypePositionMapper;

    @Autowired
    public PickStationTypeServiceImpl(PickStationTypeRepository pickStationTypeRepository,
                                      PickStationTypeMapper pickStationTypeMapper,
                                      PackingStationRepository packingStationRepository,
                                      PickStationTypePositionMapper pickStationTypePositionMapper) {
        this.pickStationTypeRepository = pickStationTypeRepository;
        this.pickStationTypeMapper = pickStationTypeMapper;
        this.packingStationRepository = packingStationRepository;
        this.pickStationTypePositionMapper = pickStationTypePositionMapper;
    }

    @Override
    public PickStationTypeDTO create(PickStationTypeDTO dto) {
        PickStationType entity = pickStationTypeMapper.toEntity(dto);
        checkPackingStationTypeName(entity.getWarehouseId(), entity.getName());
        for (PickStationTypePositionDTO positionDTO : dto.getPositions()) {
            entity.addPosition(pickStationTypePositionMapper.toEntity(positionDTO));
        }
        return pickStationTypeMapper.toDTO(pickStationTypeRepository.save(entity));
    }

    @Override
    public void delete(String id) {
        PickStationType entity = pickStationTypeRepository.retrieve(id);
        pickStationTypeRepository.delete(entity);
    }

    @Override
    public PickStationTypeDTO update(PickStationTypeDTO dto) {
        PickStationType entity = pickStationTypeRepository.retrieve(dto.getId());
        if (!(entity.getName().equalsIgnoreCase(dto.getName()))) {
            checkPackingStationTypeName(entity.getWarehouseId(), dto.getName());
        }
        pickStationTypeMapper.updateEntityFromDTO(dto, entity);
        entity.getPositions().clear();
        List<PickStationTypePosition> positions = pickStationTypePositionMapper.toEntityList(dto.getPositions());
        for (PickStationTypePosition position : positions) {
            entity.addPosition(position);
        }
        return pickStationTypeMapper.toDTO(pickStationTypeRepository.save(entity));
    }

    @Override
    public PickStationTypeDTO retrieve(String id) {
        PickStationType entity = pickStationTypeRepository.retrieve(id);
        PickStationTypeDTO dto = pickStationTypeMapper.toDTO(entity);
        dto.setPositions(pickStationTypePositionMapper.toDTOList(entity.getPositions()));
        return dto;
    }

    @Override
    public List<PickStationTypeDTO> getAll() {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "name"));
        List<PickStationType> entities = pickStationTypeRepository.getNotLockList(null, sort);
        return pickStationTypeMapper.toDTOList(entities);
    }

    @Override
    public List<PickStationTypeDTO> getBySearchTerm(String searchTerm, Sort sort) {
        List<PickStationType> entities = pickStationTypeRepository.getBySearchTerm(searchTerm, sort);
        return pickStationTypeMapper.toDTOList(entities);
    }

    @Override
    public Page<PickStationTypeDTO> getBySearchTerm(String searchTerm, Pageable pageable) {
        Page<PickStationType> entities = pickStationTypeRepository.getBySearchTerm(searchTerm, pageable);
        return pickStationTypeMapper.toDTOPage(pageable, entities);
    }

    private void checkPackingStationTypeName(String warehouse, String name) {
        PickStationType pickStationType = pickStationTypeRepository.getByName(warehouse, name);
        if (pickStationType != null) {
            throw new ApiException(OutBoundException.EX_MD_OB_PICK_STATION_NAME_UNIQUE.toString(), name);
        }
    }
}
