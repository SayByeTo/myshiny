package com.mushiny.wms.masterdata.mdbasics.service.impl;

import com.mushiny.wms.common.context.ApplicationContext;
import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.masterdata.mdbasics.business.PodBusiness;
import com.mushiny.wms.masterdata.mdbasics.business.dto.PodStorageLocationsDTO;
import com.mushiny.wms.masterdata.mdbasics.crud.dto.PodDTO;
import com.mushiny.wms.masterdata.mdbasics.crud.mapper.PodMapper;
import com.mushiny.wms.masterdata.mdbasics.domain.Pod;
import com.mushiny.wms.masterdata.mdbasics.domain.StorageLocation;
import com.mushiny.wms.masterdata.mdbasics.exception.MasterDataException;
import com.mushiny.wms.masterdata.mdbasics.repository.PodRepository;
import com.mushiny.wms.masterdata.mdbasics.repository.StorageLocationRepository;
import com.mushiny.wms.masterdata.mdbasics.service.PodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PodServiceImpl implements PodService {

    private final PodRepository podRepository;
    private final StorageLocationRepository storageLocationRepository;
    private final ApplicationContext applicationContext;
    private final PodBusiness podBusiness;
    private final PodMapper podMapper;

    @Autowired
    public PodServiceImpl(ApplicationContext applicationContext,
                          PodRepository podRepository,
                          PodMapper podMapper,
                          StorageLocationRepository storageLocationRepository,
                          PodBusiness podBusiness
    ) {
        this.applicationContext = applicationContext;
        this.podRepository = podRepository;
        this.podMapper = podMapper;
        this.storageLocationRepository = storageLocationRepository;
        this.podBusiness = podBusiness;
    }

    @Override
    public void createMore(PodStorageLocationsDTO dto) {
        podBusiness.createMore(dto);
    }

    @Override
    public PodDTO create(PodDTO dto) {
        Pod entity = podMapper.toEntity(dto);
        checkPodName(entity.getWarehouseId(), entity.getName());
        return podMapper.toDTO(podRepository.save(entity));
    }

    @Override
    public void delete(String id) {
        Pod entity = podRepository.retrieve(id);
        int maxBinIndex = storageLocationRepository.getPodMaxIndex(entity);
        int minBinIndex = storageLocationRepository.getPodMinIndex(entity);
        podRepository.updateDeletePodIndex(entity.getPodIndex(), entity.getWarehouseId());
        int subIndex = maxBinIndex - minBinIndex + 1;
        storageLocationRepository.updateDeleteBinOrderIndex(subIndex, maxBinIndex, entity.getWarehouseId());
        // 删除
        List<StorageLocation> bins = storageLocationRepository.getByPod(entity);
        if (!bins.isEmpty()) {
            storageLocationRepository.delete(bins);
        }
        podRepository.delete(entity);
    }

    @Override
    public PodDTO update(PodDTO dto) {
        Pod entity = podRepository.retrieve(dto.getId());
        podMapper.updateEntityFromDTO(dto, entity);
        return podMapper.toDTO(podRepository.save(entity));
    }

    @Override
    public PodDTO retrieve(String id) {
        return podMapper.toDTO(podRepository.retrieve(id));
    }

    @Override
    public List<PodDTO> getBySearchTerm(String searchTerm, Sort sort) {
        List<Pod> entities = podRepository.getBySearchTerm(searchTerm, sort);
        return podMapper.toDTOList(entities);
    }

    @Override
    public Page<PodDTO> getBySearchTerm(String searchTerm, Pageable pageable) {
        Page<Pod> entities = podRepository.getBySearchTerm(searchTerm, pageable);
        return podMapper.toDTOPage(pageable, entities);
    }

    @Override
    public List<PodDTO> getByClientId(String clientId) {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "podIndex"));
        applicationContext.isCurrentClient(clientId);
        List<Pod> entities = podRepository.getList(clientId, sort);
        return podMapper.toDTOList(entities);
    }

    private void checkPodName(String warehouse, String podName) {
        Pod pod = podRepository.getByName(warehouse, podName);
        if (pod != null) {
            throw new ApiException(MasterDataException.EX_MD_POD_NAME_UNIQUE.toString(), podName);
        }
    }
}
