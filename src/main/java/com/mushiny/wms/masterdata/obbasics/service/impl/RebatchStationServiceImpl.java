package com.mushiny.wms.masterdata.obbasics.service.impl;

import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.masterdata.obbasics.business.RebatchStationBusiness;
import com.mushiny.wms.masterdata.obbasics.crud.dto.RebatchStationDTO;
import com.mushiny.wms.masterdata.obbasics.crud.mapper.RebatchStationMapper;
import com.mushiny.wms.masterdata.obbasics.crud.mapper.RebatchStationPositionMapper;
import com.mushiny.wms.masterdata.obbasics.domain.RebatchStation;
import com.mushiny.wms.masterdata.obbasics.exception.OutBoundException;
import com.mushiny.wms.masterdata.obbasics.repository.RebatchStationRepository;
import com.mushiny.wms.masterdata.obbasics.service.RebatchStationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RebatchStationServiceImpl implements RebatchStationService {

    private final RebatchStationRepository rebatchStationRepository;
    private final RebatchStationMapper rebatchStationMapper;
    private final RebatchStationPositionMapper rebatchStationPositionMapper;
    private final RebatchStationBusiness rebatchStationBusiness;

    @Autowired
    public RebatchStationServiceImpl(RebatchStationRepository rebatchStationRepository,
                                     RebatchStationMapper rebatchStationMapper,
                                     RebatchStationPositionMapper rebatchStationPositionMapper,
                                     RebatchStationBusiness rebatchStationBusiness) {
        this.rebatchStationRepository = rebatchStationRepository;
        this.rebatchStationMapper = rebatchStationMapper;
        this.rebatchStationPositionMapper = rebatchStationPositionMapper;
        this.rebatchStationBusiness = rebatchStationBusiness;
    }

    @Override
    public void createMore(RebatchStationDTO dto) {
        rebatchStationBusiness.createMore(dto);
    }

    @Override
    public RebatchStationDTO create(RebatchStationDTO dto) {
        return null;
    }

    @Override
    public void delete(String id) {
        RebatchStation entity = rebatchStationRepository.retrieve(id);
        rebatchStationRepository.delete(entity);
    }

    @Override
    public RebatchStationDTO update(RebatchStationDTO dto) {
        return null;
    }

    @Override
    public RebatchStationDTO retrieve(String id) {
        RebatchStation entity = rebatchStationRepository.retrieve(id);
        RebatchStationDTO dto = rebatchStationMapper.toDTO(entity);
        dto.setPositions(rebatchStationPositionMapper.toDTOList(entity.getPositions()));
        return dto;
    }

    @Override
    public List<RebatchStationDTO> getAll() {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "name"));
        List<RebatchStation> entities = rebatchStationRepository.getNotLockList(null, sort);
        return rebatchStationMapper.toDTOList(entities);
    }

    @Override
    public List<RebatchStationDTO> getBySearchTerm(String searchTerm, Sort sort) {
        List<RebatchStation> entities = rebatchStationRepository.getBySearchTerm(searchTerm, sort);
        return rebatchStationMapper.toDTOList(entities);
    }

    @Override
    public Page<RebatchStationDTO> getBySearchTerm(String searchTerm, Pageable pageable) {
        Page<RebatchStation> entities = rebatchStationRepository.getBySearchTerm(searchTerm, pageable);
        return rebatchStationMapper.toDTOPage(pageable, entities);
    }

    private void checkPackingStationName(String warehouse, String name) {
        RebatchStation packingStation = rebatchStationRepository.getByName(warehouse, name);
        if (packingStation != null) {
            throw new ApiException(OutBoundException.EX_MD_OB_PACKING_STATION_NAME_UNIQUE.toString(), name);
        }
    }
}
