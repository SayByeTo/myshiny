package com.mushiny.wms.masterdata.obbasics.service.impl;

import com.mushiny.wms.common.context.ApplicationContext;
import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.masterdata.obbasics.crud.dto.PickPackCellDTO;
import com.mushiny.wms.masterdata.obbasics.crud.mapper.PickPackCellMapper;
import com.mushiny.wms.masterdata.obbasics.domain.PickPackCell;
import com.mushiny.wms.masterdata.obbasics.exception.OutBoundException;
import com.mushiny.wms.masterdata.obbasics.repository.PickPackCellRepository;
import com.mushiny.wms.masterdata.obbasics.service.PickPackCellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PickPackCellServiceImpl implements PickPackCellService {

    private final PickPackCellRepository pickPackCellRepository;
    private final ApplicationContext applicationContext;
    private final PickPackCellMapper pickPackCellMapper;

    @Autowired
    public PickPackCellServiceImpl(PickPackCellRepository pickPackCellRepository,
                                   ApplicationContext applicationContext,
                                   PickPackCellMapper pickPackCellMapper) {
        this.pickPackCellRepository = pickPackCellRepository;
        this.applicationContext = applicationContext;
        this.pickPackCellMapper = pickPackCellMapper;
    }

    @Override
    public PickPackCellDTO create(PickPackCellDTO dto) {
        PickPackCell entity = pickPackCellMapper.toEntity(dto);
        checkName(entity.getWarehouseId(), entity.getName());
        return pickPackCellMapper.toDTO(pickPackCellRepository.save(entity));
    }

    @Override
    public void delete(String id) {
        PickPackCell entity = pickPackCellRepository.retrieve(id);
        pickPackCellRepository.delete(entity);
    }

    @Override
    public PickPackCellDTO update(PickPackCellDTO dto) {
        PickPackCell entity = pickPackCellRepository.retrieve(dto.getId());
        if (!(entity.getName().equalsIgnoreCase(dto.getName()))) {
            checkName(entity.getWarehouseId(), dto.getName());
        }
        pickPackCellMapper.updateEntityFromDTO(dto, entity);
        return pickPackCellMapper.toDTO(pickPackCellRepository.save(entity));
    }

    @Override
    public PickPackCellDTO retrieve(String id) {
        return pickPackCellMapper.toDTO(pickPackCellRepository.retrieve(id));
    }

    @Override
    public List<PickPackCellDTO> getBySearchTerm(String searchTerm, Sort sort) {
        List<PickPackCell> entities = pickPackCellRepository.getBySearchTerm(searchTerm, sort);
        return pickPackCellMapper.toDTOList(entities);
    }

    @Override
    public Page<PickPackCellDTO> getBySearchTerm(String searchTerm, Pageable pageable) {
        Page<PickPackCell> entities = pickPackCellRepository.getBySearchTerm(searchTerm, pageable);
        return pickPackCellMapper.toDTOPage(pageable, entities);
    }

    private void checkName(String warehouse, String areaName) {
        PickPackCell pickPackCell = pickPackCellRepository.getByName(warehouse, areaName);
        if (pickPackCell != null) {
            throw new ApiException(OutBoundException.EX_MD_OB_PICKPACK_CELL_NAME_UNIQUE.toString(), areaName);
        }
    }
}