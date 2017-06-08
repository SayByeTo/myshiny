package com.mushiny.wms.masterdata.mdbasics.service.impl;

import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.masterdata.mdbasics.crud.dto.WorkStationDTO;
import com.mushiny.wms.masterdata.mdbasics.crud.dto.WorkStationPositionDTO;
import com.mushiny.wms.masterdata.mdbasics.crud.mapper.WorkStationMapper;
import com.mushiny.wms.masterdata.mdbasics.crud.mapper.WorkStationPositionMapper;
import com.mushiny.wms.masterdata.mdbasics.domain.WorkStation;
import com.mushiny.wms.masterdata.mdbasics.domain.WorkStationPosition;
import com.mushiny.wms.masterdata.mdbasics.exception.MasterDataException;
import com.mushiny.wms.masterdata.mdbasics.repository.WorkStationRepository;
import com.mushiny.wms.masterdata.mdbasics.service.WorkStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class WorkStationServiceImpl implements WorkStationService {

    private final WorkStationRepository workStationRepository;
    private final WorkStationMapper workStationMapper;
    private final WorkStationPositionMapper workStationPositionMapper;

    @Autowired
    public WorkStationServiceImpl(WorkStationRepository workStationRepository,
                                  WorkStationMapper workStationMapper,
                                  WorkStationPositionMapper workStationPositionMapper) {
        this.workStationRepository = workStationRepository;
        this.workStationMapper = workStationMapper;
        this.workStationPositionMapper = workStationPositionMapper;
    }

    @Override
    public WorkStationDTO create(WorkStationDTO dto) {
        WorkStation entity = workStationMapper.toEntity(dto);
        checkName(entity.getWarehouseId(), entity.getName());
        for (WorkStationPositionDTO positionDTO : dto.getPositions()) {
            entity.addPosition(workStationPositionMapper.toEntity(positionDTO));
        }
        return workStationMapper.toDTO(workStationRepository.save(entity));
    }

    @Override
    public void delete(String id) {
        WorkStation entity = workStationRepository.retrieve(id);
        workStationRepository.delete(entity);
    }

    @Override
    public WorkStationDTO update(WorkStationDTO dto) {
        WorkStation entity = workStationRepository.retrieve(dto.getId());
        if (!(entity.getName().equalsIgnoreCase(dto.getName()))) {
            checkName(entity.getWarehouseId(), dto.getName());
        }
        workStationMapper.updateEntityFromDTO(dto, entity);
        entity.getPositions().clear();
        List<WorkStationPosition> positions = workStationPositionMapper.toEntityList(dto.getPositions());
        for (WorkStationPosition position : positions) {
            entity.addPosition(position);
        }
        return workStationMapper.toDTO(workStationRepository.save(entity));
    }

    @Override
    public WorkStationDTO retrieve(String id) {
        WorkStation entity = workStationRepository.retrieve(id);
        WorkStationDTO dto = workStationMapper.toDTO(entity);
        dto.setPositions(workStationPositionMapper.toDTOList(entity.getPositions()));
        return dto;
    }

    @Override
    public List<WorkStationDTO> getBySearchTerm(String searchTerm, Sort sort) {
        List<WorkStation> entities = workStationRepository.getBySearchTerm(searchTerm, sort);
        return workStationMapper.toDTOList(entities);
    }

    @Override
    public Page<WorkStationDTO> getBySearchTerm(String searchTerm, Pageable pageable) {
        Page<WorkStation> entities = workStationRepository.getBySearchTerm(searchTerm, pageable);
        return workStationMapper.toDTOPage(pageable, entities);
    }

    @Override
    public List<WorkStationDTO> getAll() {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "name"));
        List<WorkStation> entities = workStationRepository.getList(null, sort);
        return workStationMapper.toDTOList(entities);
    }

    private void checkName(String warehouse, String bayTypeName) {
        WorkStation podType = workStationRepository.getByName(warehouse, bayTypeName);
        if (podType != null) {
            throw new ApiException(MasterDataException.EX_MD_WORK_STATION_NAME_UNIQUE.toString(), bayTypeName);
        }
    }
}
