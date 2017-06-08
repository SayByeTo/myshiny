package com.mushiny.wms.masterdata.ibbasics.service.impl;

import com.mushiny.wms.common.Constant;
import com.mushiny.wms.common.context.ApplicationContext;
import com.mushiny.wms.common.utils.RandomUtil;
import com.mushiny.wms.masterdata.ibbasics.crud.dto.AdviceRequestDTO;
import com.mushiny.wms.masterdata.ibbasics.crud.dto.AdviceRequestPositionDTO;
import com.mushiny.wms.masterdata.ibbasics.crud.mapper.AdviceRequestMapper;
import com.mushiny.wms.masterdata.ibbasics.crud.mapper.AdviceRequestPositionMapper;
import com.mushiny.wms.masterdata.ibbasics.domain.AdviceRequest;
import com.mushiny.wms.masterdata.ibbasics.domain.AdviceRequestPosition;
import com.mushiny.wms.masterdata.ibbasics.domain.enums.AdviceRequestState;
import com.mushiny.wms.masterdata.ibbasics.repository.AdviceRequestRepository;
import com.mushiny.wms.masterdata.ibbasics.service.AdviceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AdviceRequestServiceImpl implements AdviceRequestService {

    private final AdviceRequestRepository adviceRequestRepository;
    private final ApplicationContext applicationContext;
    private final AdviceRequestMapper adviceRequestMapper;
    private final AdviceRequestPositionMapper adviceRequestPositionMapper;

    @Autowired
    public AdviceRequestServiceImpl(AdviceRequestRepository adviceRequestRepository,
                                    ApplicationContext applicationContext,
                                    AdviceRequestMapper adviceRequestMapper,
                                    AdviceRequestPositionMapper adviceRequestPositionMapper) {
        this.adviceRequestRepository = adviceRequestRepository;
        this.applicationContext = applicationContext;
        this.adviceRequestMapper = adviceRequestMapper;
        this.adviceRequestPositionMapper = adviceRequestPositionMapper;
    }

    @Override
    public AdviceRequestDTO create(AdviceRequestDTO dto) {
        AdviceRequest entity = adviceRequestMapper.toEntity(dto);
        entity.setAdviceState(AdviceRequestState.Raw.toString());
        String adviceNo;
        boolean randomFlag = true;
        while (randomFlag) {
            adviceNo = "DN" + RandomUtil.getAdviceNo();
            AdviceRequest adviceRequest = adviceRequestRepository.getByAdviceNo(adviceNo);
            if (adviceRequest == null) {
                entity.setAdviceNo(adviceNo);
                randomFlag = false;
            }
        }
        for (AdviceRequestPositionDTO positionDTO : dto.getPositions()) {
            entity.addPosition(adviceRequestPositionMapper.toEntity(positionDTO));
        }
        return adviceRequestMapper.toDTO(adviceRequestRepository.save(entity));
    }

    @Override
    public void delete(String id) {
        AdviceRequest entity = adviceRequestRepository.retrieve(id);
        entity.setEntityLock(Constant.GOING_TO_DELETE);
        adviceRequestRepository.save(entity);
    }

    @Override
    public AdviceRequestDTO update(AdviceRequestDTO dto) {
        AdviceRequest entity = adviceRequestRepository.retrieve(dto.getId());
        adviceRequestMapper.updateEntityFromDTO(dto, entity);
        entity.getPositions().clear();
        List<AdviceRequestPosition> positions = adviceRequestPositionMapper.toEntityList(dto.getPositions());
        for (AdviceRequestPosition position : positions) {
            entity.addPosition(position);
        }
        return adviceRequestMapper.toDTO(adviceRequestRepository.save(entity));
    }

    @Override
    public AdviceRequestDTO retrieve(String id) {
        AdviceRequest entity = adviceRequestRepository.retrieve(id);
        AdviceRequestDTO dto = adviceRequestMapper.toDTO(entity);
        dto.getPositions().addAll(adviceRequestPositionMapper.toDTOList(entity.getPositions()));
        return dto;
    }

    @Override
    public List<AdviceRequestDTO> getBySearchTerm(String searchTerm, Sort sort) {
        List<AdviceRequest> entities = adviceRequestRepository.getBySearchTerm(searchTerm, sort);
        return adviceRequestMapper.toDTOList(entities);
    }

    @Override
    public Page<AdviceRequestDTO> getBySearchTerm(String searchTerm, Pageable pageable) {
        Page<AdviceRequest> entities = adviceRequestRepository.getBySearchTerm(searchTerm, pageable);
        return adviceRequestMapper.toDTOPage(pageable, entities);
    }

    @Override
    public List<AdviceRequestDTO> getByClientId(String clientId) {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "adviceNo"));
        applicationContext.isCurrentClient(clientId);
        List<AdviceRequest> entities = adviceRequestRepository.getNotLockList(clientId, sort);
        return adviceRequestMapper.toDTOList(entities);
    }
}
