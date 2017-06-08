package com.mushiny.wms.masterdata.obbasics.service.impl;

import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.masterdata.obbasics.crud.dto.PickingCateGoryRuleDTO;
import com.mushiny.wms.masterdata.obbasics.crud.dto.PickingCateGoryRulePositionDTO;
import com.mushiny.wms.masterdata.obbasics.crud.mapper.PickingCateGoryRuleMapper;
import com.mushiny.wms.masterdata.obbasics.crud.mapper.PickingCateGoryRulePositionMapper;
import com.mushiny.wms.masterdata.obbasics.domain.PickingCateGoryRule;
import com.mushiny.wms.masterdata.obbasics.domain.PickingCateGoryRulePosition;
import com.mushiny.wms.masterdata.obbasics.exception.OutBoundException;
import com.mushiny.wms.masterdata.obbasics.repository.PickingCateGoryRuleRepository;
import com.mushiny.wms.masterdata.obbasics.service.PickingCateGoryRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PickingCateGoryRuleServiceImpl implements PickingCateGoryRuleService {

    private final PickingCateGoryRuleRepository pickingCateGoryRuleRepository;
    private final PickingCateGoryRuleMapper pickingCateGoryRuleMapper;
    private final PickingCateGoryRulePositionMapper pickingCateGoryRulePositionMapper;

    @Autowired
    public PickingCateGoryRuleServiceImpl(PickingCateGoryRuleRepository pickingCateGoryRuleRepository,
                                          PickingCateGoryRuleMapper pickingCateGoryRuleMapper,
                                          PickingCateGoryRulePositionMapper pickingCateGoryRulePositionMapper) {
        this.pickingCateGoryRuleRepository = pickingCateGoryRuleRepository;
        this.pickingCateGoryRuleMapper = pickingCateGoryRuleMapper;
        this.pickingCateGoryRulePositionMapper = pickingCateGoryRulePositionMapper;
    }

    @Override
    public PickingCateGoryRuleDTO create(PickingCateGoryRuleDTO dto) {
        PickingCateGoryRule entity = pickingCateGoryRuleMapper.toEntity(dto);
        for (PickingCateGoryRulePositionDTO positionDTO : dto.getPositions()) {
            entity.addPosition(pickingCateGoryRulePositionMapper.toEntity(positionDTO));
        }
        return pickingCateGoryRuleMapper.toDTO(pickingCateGoryRuleRepository.save(entity));
    }

    @Override
    public void delete(String id) {
        PickingCateGoryRule entity = pickingCateGoryRuleRepository.retrieve(id);
        pickingCateGoryRuleRepository.delete(entity);
    }

    @Override
    public PickingCateGoryRuleDTO update(PickingCateGoryRuleDTO dto) {
        PickingCateGoryRule entity = pickingCateGoryRuleRepository.retrieve(dto.getId());
        pickingCateGoryRuleMapper.updateEntityFromDTO(dto, entity);
        entity.getPositions().clear();
        List<PickingCateGoryRulePosition> positions = pickingCateGoryRulePositionMapper.toEntityList(dto.getPositions());
        for (PickingCateGoryRulePosition position : positions) {
            entity.addPosition(position);
        }
        return pickingCateGoryRuleMapper.toDTO(pickingCateGoryRuleRepository.save(entity));
    }

    @Override
    public PickingCateGoryRuleDTO retrieve(String id) {
        PickingCateGoryRule entity = pickingCateGoryRuleRepository.retrieve(id);
        PickingCateGoryRuleDTO dto = pickingCateGoryRuleMapper.toDTO(entity);
        dto.setPositions(pickingCateGoryRulePositionMapper.toDTOList(entity.getPositions()));
        return dto;
    }

    @Override
    public List<PickingCateGoryRuleDTO> getAll() {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "name"));
        List<PickingCateGoryRule> entities = pickingCateGoryRuleRepository.getNotLockList(null, sort);
        return pickingCateGoryRuleMapper.toDTOList(entities);
    }

    @Override
    public List<PickingCateGoryRuleDTO> getBySearchTerm(String searchTerm, Sort sort) {
        List<PickingCateGoryRule> entities = pickingCateGoryRuleRepository.getBySearchTerm(searchTerm, sort);
        return pickingCateGoryRuleMapper.toDTOList(entities);
    }

    @Override
    public Page<PickingCateGoryRuleDTO> getBySearchTerm(String searchTerm, Pageable pageable) {
        Page<PickingCateGoryRule> entities = pickingCateGoryRuleRepository.getBySearchTerm(searchTerm, pageable);
        return pickingCateGoryRuleMapper.toDTOPage(pageable, entities);
    }

}
