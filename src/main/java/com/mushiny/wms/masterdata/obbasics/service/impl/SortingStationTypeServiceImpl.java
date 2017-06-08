package com.mushiny.wms.masterdata.obbasics.service.impl;

import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.masterdata.obbasics.crud.dto.SortingStationTypeDTO;
import com.mushiny.wms.masterdata.obbasics.crud.dto.SortingStationTypePositionDTO;
import com.mushiny.wms.masterdata.obbasics.crud.mapper.SortingStationTypeMapper;
import com.mushiny.wms.masterdata.obbasics.crud.mapper.SortingStationTypePositionMapper;
import com.mushiny.wms.masterdata.obbasics.domain.SortingStationType;
import com.mushiny.wms.masterdata.obbasics.domain.SortingStationTypePosition;
import com.mushiny.wms.masterdata.obbasics.exception.OutBoundException;
import com.mushiny.wms.masterdata.obbasics.repository.SortingStationTypeRepository;
import com.mushiny.wms.masterdata.obbasics.service.SortingStationTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class SortingStationTypeServiceImpl implements SortingStationTypeService {

    private final SortingStationTypeRepository sortingStationTypeRepository;
    private final SortingStationTypeMapper sortingStationTypeMapper;
    private final SortingStationTypePositionMapper sortingStationTypePositionMapper;

    @Autowired
    public SortingStationTypeServiceImpl(SortingStationTypeRepository sortingStationTypeRepository,
                                         SortingStationTypeMapper sortingStationTypeMapper,
                                         SortingStationTypePositionMapper sortingStationTypePositionMapper) {
        this.sortingStationTypeRepository = sortingStationTypeRepository;
        this.sortingStationTypeMapper = sortingStationTypeMapper;
        this.sortingStationTypePositionMapper = sortingStationTypePositionMapper;
    }

    @Override
    public SortingStationTypeDTO create(SortingStationTypeDTO dto) {
        SortingStationType entity = sortingStationTypeMapper.toEntity(dto);
        checkSortingStationTypeName(entity.getWarehouseId(), entity.getName());
        for (SortingStationTypePositionDTO positionDTO : dto.getPositions()) {
            entity.addPosition(sortingStationTypePositionMapper.toEntity(positionDTO));
        }
        return sortingStationTypeMapper.toDTO(sortingStationTypeRepository.save(entity));
    }

    @Override
    public void delete(String id) {
        SortingStationType entity = sortingStationTypeRepository.retrieve(id);
        sortingStationTypeRepository.delete(entity);
    }

    @Override
    public SortingStationTypeDTO update(SortingStationTypeDTO dto) {
        SortingStationType entity = sortingStationTypeRepository.retrieve(dto.getId());
        if (!(entity.getName().equalsIgnoreCase(dto.getName()))) {
            checkSortingStationTypeName(entity.getWarehouseId(), dto.getName());
        }
        sortingStationTypeMapper.updateEntityFromDTO(dto, entity);
        entity.getPositions().clear();
        List<SortingStationTypePosition> positions = sortingStationTypePositionMapper.toEntityList(dto.getPositions());
        for (SortingStationTypePosition position : positions) {
            entity.addPosition(position);
        }
        return sortingStationTypeMapper.toDTO(sortingStationTypeRepository.save(entity));
    }

    @Override
    public SortingStationTypeDTO retrieve(String id) {
        SortingStationType entity = sortingStationTypeRepository.retrieve(id);
        SortingStationTypeDTO dto = sortingStationTypeMapper.toDTO(entity);
        dto.setPositions(sortingStationTypePositionMapper.toDTOList(entity.getPositions()));
        return dto;
    }

    @Override
    public List<SortingStationTypeDTO> getAll() {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "name"));
        List<SortingStationType> entities = sortingStationTypeRepository.getNotLockList(null, sort);
        return sortingStationTypeMapper.toDTOList(entities);
    }

    @Override
    public List<SortingStationTypeDTO> getBySearchTerm(String searchTerm, Sort sort) {
        List<SortingStationType> entities = sortingStationTypeRepository.getBySearchTerm(searchTerm, sort);
        return sortingStationTypeMapper.toDTOList(entities);
    }

    @Override
    public Page<SortingStationTypeDTO> getBySearchTerm(String searchTerm, Pageable pageable) {
        Page<SortingStationType> entityPage = sortingStationTypeRepository.getBySearchTerm(searchTerm, pageable);
        return sortingStationTypeMapper.toDTOPage(pageable, entityPage);
    }

    private void checkSortingStationTypeName(String warehouse, String name) {
        SortingStationType sortingStationType = sortingStationTypeRepository.getByName(warehouse, name);
        if (sortingStationType != null) {
            throw new ApiException(OutBoundException.EX_MD_OB_SORTING_STATION_TYPE_NAME_UNIQUE.toString(), name);
        }
    }
}
