package com.mushiny.wms.masterdata.obbasics.service.impl;

import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.masterdata.obbasics.crud.dto.PickingCateGoryDTO;
import com.mushiny.wms.masterdata.obbasics.crud.dto.PickingCateGoryPositionDTO;
import com.mushiny.wms.masterdata.obbasics.crud.mapper.PickingCateGoryMapper;
import com.mushiny.wms.masterdata.obbasics.crud.mapper.PickingCateGoryPositionMapper;
import com.mushiny.wms.masterdata.obbasics.domain.PickingCateGory;
import com.mushiny.wms.masterdata.obbasics.domain.PickingCateGoryPosition;
import com.mushiny.wms.masterdata.obbasics.exception.OutBoundException;
import com.mushiny.wms.masterdata.obbasics.repository.PickingCateGoryPositionRepository;
import com.mushiny.wms.masterdata.obbasics.repository.PickingCateGoryRepository;
import com.mushiny.wms.masterdata.obbasics.service.PickingCateGoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PickingCateGoryServiceImpl implements PickingCateGoryService {

    private final PickingCateGoryRepository pickingCateGoryRepository;
    private final PickingCateGoryPositionRepository pickingCateGoryPositionRepository;
    private final PickingCateGoryMapper pickingCateGoryMapper;
    private final PickingCateGoryPositionMapper pickingCateGoryPositionMapper;

    @Autowired
    public PickingCateGoryServiceImpl(PickingCateGoryRepository pickingCateGoryRepository,
                                      PickingCateGoryPositionRepository pickingCateGoryPositionRepository,
                                      PickingCateGoryMapper pickingCateGoryMapper,
                                      PickingCateGoryPositionMapper pickingCateGoryPositionMapper) {
        this.pickingCateGoryRepository = pickingCateGoryRepository;
        this.pickingCateGoryPositionRepository = pickingCateGoryPositionRepository;
        this.pickingCateGoryMapper = pickingCateGoryMapper;
        this.pickingCateGoryPositionMapper = pickingCateGoryPositionMapper;
    }

    @Override
    public PickingCateGoryDTO create(PickingCateGoryDTO dto) {
        PickingCateGory entity = pickingCateGoryMapper.toEntity(dto);
        checkName(entity.getWarehouseId(),entity.getClientId(),entity.getName());
        for (PickingCateGoryPositionDTO positionDTO : dto.getPositions()) {
            checkPosition(positionDTO.getWarehouseId(),positionDTO.getClientId(),positionDTO.getPositionNo());
            entity.addPosition(pickingCateGoryPositionMapper.toEntity(positionDTO));
        }
        return pickingCateGoryMapper.toDTO(pickingCateGoryRepository.save(entity));
    }

    @Override
    public void delete(String id) {
        PickingCateGory entity = pickingCateGoryRepository.retrieve(id);
        pickingCateGoryRepository.delete(entity);
    }

    @Override
    public PickingCateGoryDTO update(PickingCateGoryDTO dto) {
        PickingCateGory entity = pickingCateGoryRepository.retrieve(dto.getId());
        if (!(entity.getName().equalsIgnoreCase(dto.getName()))) {
            checkName(entity.getWarehouseId(), dto.getClientId(), dto.getName());
        }
        pickingCateGoryMapper.updateEntityFromDTO(dto, entity);
        entity.getPositions().clear();
        List<PickingCateGoryPosition> positions = pickingCateGoryPositionMapper.toEntityList(dto.getPositions());
        for (PickingCateGoryPosition position : positions) {
            checkPosition(position.getWarehouseId(),position.getClientId(),position.getPositionNo());
            entity.addPosition(position);
        }
        return pickingCateGoryMapper.toDTO(pickingCateGoryRepository.save(entity));
    }

    @Override
    public PickingCateGoryDTO retrieve(String id) {
        PickingCateGory entity = pickingCateGoryRepository.retrieve(id);
        PickingCateGoryDTO dto = pickingCateGoryMapper.toDTO(entity);
        dto.setPositions(pickingCateGoryPositionMapper.toDTOList(entity.getPositions()));
        return dto;
    }

    @Override
    public List<PickingCateGoryDTO> getAll() {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "name"));
        List<PickingCateGory> entities = pickingCateGoryRepository.getNotLockList(null, sort);
        return pickingCateGoryMapper.toDTOList(entities);
    }

    @Override
    public List<PickingCateGoryDTO> getBySearchTerm(String searchTerm, Sort sort) {
        List<PickingCateGory> entities = pickingCateGoryRepository.getBySearchTerm(searchTerm, sort);
        return pickingCateGoryMapper.toDTOList(entities);
    }

    @Override
    public Page<PickingCateGoryDTO> getBySearchTerm(String searchTerm, Pageable pageable) {
        Page<PickingCateGory> entities = pickingCateGoryRepository.getBySearchTerm(searchTerm, pageable);
        return pickingCateGoryMapper.toDTOPage(pageable, entities);
    }

    private void checkName(String warehouse, String client, String areaName) {
        PickingCateGory pickingCateGory = pickingCateGoryRepository.getByName(warehouse, client, areaName);
        if (pickingCateGory != null) {
            throw new ApiException(OutBoundException.EX_MD_OB_PICKING_CATE_GORY_NAME_UNIQUE.toString(), areaName);
        }
    }

    private void checkPosition(String warehouse, String client, String positionNo) {
        PickingCateGoryPosition pickingCateGoryPosition = pickingCateGoryPositionRepository.getByName(warehouse, client, positionNo);
        if (pickingCateGoryPosition != null) {
            throw new ApiException(OutBoundException.EX_MD_OB_PICKING_CATE_GORY_POSITION_POSITIONNO_UNIQUE.toString(), positionNo);
        }
    }
}
