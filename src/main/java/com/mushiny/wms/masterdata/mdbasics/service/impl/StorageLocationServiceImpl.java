package com.mushiny.wms.masterdata.mdbasics.service.impl;

import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.common.utils.LevelUtil;
import com.mushiny.wms.common.context.ApplicationContext;
import com.mushiny.wms.masterdata.mdbasics.crud.dto.StorageLocationDTO;
import com.mushiny.wms.masterdata.mdbasics.crud.mapper.StorageLocationMapper;
import com.mushiny.wms.masterdata.mdbasics.domain.StorageLocation;
import com.mushiny.wms.masterdata.mdbasics.exception.MasterDataException;
import com.mushiny.wms.masterdata.mdbasics.repository.StorageLocationRepository;
import com.mushiny.wms.masterdata.mdbasics.service.StorageLocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StorageLocationServiceImpl implements StorageLocationService {

    private final StorageLocationRepository storageLocationRepository;
    private final ApplicationContext applicationContext;
    private final StorageLocationMapper storageLocationMapper;

    @Autowired
    public StorageLocationServiceImpl(ApplicationContext applicationContext,
                                      StorageLocationRepository storageLocationRepository,
                                      StorageLocationMapper storageLocationMapper) {
        this.applicationContext = applicationContext;
        this.storageLocationRepository = storageLocationRepository;
        this.storageLocationMapper = storageLocationMapper;
    }

    @Override
    public StorageLocationDTO create(StorageLocationDTO dto) {
        StorageLocation entity = storageLocationMapper.toEntity(dto);
        entity.setName(dto.getName());
        checkStorageLocationName(entity.getWarehouseId(), entity.getName());
        return storageLocationMapper.toDTO(storageLocationRepository.save(entity));
    }

    @Override
    public void delete(String id) {
        StorageLocation entity = storageLocationRepository.retrieve(id);
        storageLocationRepository.delete(entity);
    }

    @Override
    public StorageLocationDTO update(StorageLocationDTO dto) {
        return dto;
    }

    @Override
    public StorageLocationDTO retrieve(String id) {
        return storageLocationMapper.toDTO(storageLocationRepository.retrieve(id));
    }

    @Override
    public List<StorageLocationDTO> getBySearchTerm(String searchTerm, Sort sort) {
        List<StorageLocation> entities = storageLocationRepository.getBySearchTerm(searchTerm, sort);
        return storageLocationMapper.toDTOList(entities);
    }

    @Override
    public Page<StorageLocationDTO> getBySearchTerm(String searchTerm, Pageable pageable) {
        Page<StorageLocation> entities = storageLocationRepository.getBySearchTerm(searchTerm, pageable);
        return storageLocationMapper.toDTOPage(pageable, entities);
    }

    @Override
    public List<StorageLocationDTO> getByClientId(String clientId) {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "name"));
        applicationContext.isCurrentClient(clientId);
        List<StorageLocation> entities = storageLocationRepository.getList(clientId, sort);
        return storageLocationMapper.toDTOList(entities);
    }

    private void checkStorageLocationName(String warehouse, String name) {
        StorageLocation storageLocation = storageLocationRepository.getByName(warehouse, name);
        if (storageLocation != null) {
            throw new ApiException(MasterDataException.EX_MD_STORAGE_LOCATION_NAME_UNIQUE.toString(), name);
        }
    }
}
