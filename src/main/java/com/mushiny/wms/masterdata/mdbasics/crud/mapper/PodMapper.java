package com.mushiny.wms.masterdata.mdbasics.crud.mapper;

import com.mushiny.wms.common.context.ApplicationContext;
import com.mushiny.wms.common.crud.mapper.BaseMapper;
import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.common.exception.ExceptionEnum;
import com.mushiny.wms.masterdata.mdbasics.crud.dto.PodDTO;
import com.mushiny.wms.masterdata.mdbasics.domain.Pod;
import com.mushiny.wms.masterdata.mdbasics.repository.PodTypeRepository;
import com.mushiny.wms.masterdata.mdbasics.repository.ZoneRepository;
import com.mushiny.wms.masterdata.general.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PodMapper implements BaseMapper<PodDTO, Pod> {

    private final PodTypeRepository bayTypeRepository;
    private final ApplicationContext applicationContext;
    private final PodTypeMapper bayTypeMapper;
    private final ZoneMapper zoneMapper;
    private final ZoneRepository zoneRepository;
    private final ClientRepository clientRepository;


    @Autowired
    public PodMapper(PodTypeMapper bayTypeMapper,
                     PodTypeRepository bayTypeRepository,
                     ApplicationContext applicationContext,
                     ZoneMapper zoneMapper,
                     ZoneRepository zoneRepository,
                     ClientRepository clientRepository) {
        this.bayTypeMapper = bayTypeMapper;
        this.bayTypeRepository = bayTypeRepository;
        this.applicationContext = applicationContext;
        this.zoneMapper = zoneMapper;
        this.zoneRepository = zoneRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public PodDTO toDTO(Pod entity) {
        if (entity == null) {
            return null;
        }

        PodDTO dto = new PodDTO(entity);

        dto.setName(entity.getName());
        dto.setDescription(entity.getDescription());
        dto.setPodIndex(entity.getPodIndex());

        dto.setPodType(bayTypeMapper.toDTO(entity.getPodType()));
        dto.setSellingDegree(entity.getSellingDegree());
        dto.setPlaceMark(entity.getPlaceMark());
        dto.setZone(zoneMapper.toDTO(entity.getZone()));
        dto.setClient(clientRepository.retrieve(entity.getClientId()).getName());
        dto.setClientId(entity.getClientId());
        dto.setWarehouseId(entity.getWarehouseId());

        return dto;
    }

    @Override
    public Pod toEntity(PodDTO dto) {
        if (dto == null) {
            return null;
        }

        Pod entity = new Pod();

        entity.setId(entity.getId());
        entity.setAdditionalContent(entity.getAdditionalContent());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setPodIndex(dto.getPodIndex());
        entity.setSellingDegree(dto.getSellingDegree());
        entity.setPlaceMark(dto.getPlaceMark());
        applicationContext.isCurrentClient(dto.getClientId());
        entity.setClientId(dto.getClientId());
        entity.setWarehouseId(applicationContext.getCurrentWarehouse());
        if (dto.getPodTypeId() != null) {
            entity.setPodType(bayTypeRepository.retrieve(dto.getPodTypeId()));
        }
        if (dto.getZoneId() != null) {
            entity.setZone(zoneRepository.retrieve(dto.getZoneId()));
        }

        return entity;
    }

    @Override
    public void updateEntityFromDTO(PodDTO dto, Pod entity) {
        if (dto == null || entity == null) {
            throw new ApiException(ExceptionEnum.EX_SERVER_ERROR.toString());
        }

        entity.setId(dto.getId());
        entity.setAdditionalContent(dto.getAdditionalContent());
        entity.setDescription(dto.getDescription());
        entity.setSellingDegree(dto.getSellingDegree());
        entity.setPlaceMark(dto.getPlaceMark());
        if (dto.getPodTypeId() != null) {
            entity.setPodType(bayTypeRepository.retrieve(dto.getPodTypeId()));
        }
        if (dto.getZoneId() != null) {
            entity.setZone(zoneRepository.retrieve(dto.getZoneId()));
        }
    }
}
