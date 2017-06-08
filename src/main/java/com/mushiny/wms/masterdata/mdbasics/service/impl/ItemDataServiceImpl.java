package com.mushiny.wms.masterdata.mdbasics.service.impl;

import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.common.context.ApplicationContext;
import com.mushiny.wms.masterdata.mdbasics.crud.dto.ItemDataDTO;
import com.mushiny.wms.masterdata.mdbasics.crud.mapper.ItemDataMapper;
import com.mushiny.wms.masterdata.mdbasics.domain.ItemData;
import com.mushiny.wms.masterdata.mdbasics.exception.MasterDataException;
import com.mushiny.wms.masterdata.mdbasics.repository.ItemDataRepository;
import com.mushiny.wms.masterdata.mdbasics.service.ItemDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemDataServiceImpl implements ItemDataService {

    private final ItemDataRepository itemDataRepository;
    private final ApplicationContext applicationContext;
    private final ItemDataMapper itemDataMapper;

    @Autowired
    public ItemDataServiceImpl(ItemDataRepository itemDataRepository,
                               ApplicationContext applicationContext,
                               ItemDataMapper itemDataMapper) {
        this.itemDataRepository = itemDataRepository;
        this.applicationContext = applicationContext;
        this.itemDataMapper = itemDataMapper;
    }

    @Override
    public ItemDataDTO create(ItemDataDTO dto) {
        ItemData entity = itemDataMapper.toEntity(dto);
        checkItemNo(entity.getWarehouseId(), entity.getClientId(), entity.getItemNo());
        return itemDataMapper.toDTO(itemDataRepository.save(entity));
    }

    @Override
    public void delete(String id) {
        ItemData entity = itemDataRepository.retrieve(id);
        itemDataRepository.delete(entity);
    }

    @Override
    public ItemDataDTO update(ItemDataDTO dto) {
        ItemData entity = itemDataRepository.retrieve(dto.getId());
        itemDataMapper.updateEntityFromDTO(dto, entity);
        return itemDataMapper.toDTO(itemDataRepository.save(entity));
    }

    @Override
    public ItemDataDTO retrieve(String id) {
        return itemDataMapper.toDTO(itemDataRepository.retrieve(id));
    }

    @Override
    public List<ItemDataDTO> getBySearchTerm(String searchTerm, Sort sort) {
        List<ItemData> entities = itemDataRepository.getBySearchTerm(searchTerm, sort);
        return itemDataMapper.toDTOList(entities);
    }

    @Override
    public Page<ItemDataDTO> getBySearchTerm(String searchTerm, Pageable pageable) {
        Page<ItemData> entities = itemDataRepository.getBySearchTerm(searchTerm, pageable);
        return itemDataMapper.toDTOPage(pageable, entities);
    }

    @Override
    public List<ItemDataDTO> getByClientId(String clientId) {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "itemNo"));
        applicationContext.isCurrentClient(clientId);
        List<ItemData> entities = itemDataRepository.getList(clientId, sort);
        return itemDataMapper.toDTOList(entities);
    }

    private void checkItemNo(String warehouse, String client, String itemNo) {
        ItemData itemData = itemDataRepository.getByItemNo(warehouse, client, itemNo);
        if (itemData != null) {
            throw new ApiException(MasterDataException.EX_MD_ITEM_DATA_NO_UNIQUE.toString(), client);
        }
    }
}
