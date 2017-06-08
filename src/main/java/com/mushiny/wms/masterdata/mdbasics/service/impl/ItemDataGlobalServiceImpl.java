package com.mushiny.wms.masterdata.mdbasics.service.impl;

import com.mushiny.wms.common.utils.RandomUtil;
import com.mushiny.wms.common.context.ApplicationContext;
import com.mushiny.wms.masterdata.mdbasics.crud.dto.ItemDataGlobalDTO;
import com.mushiny.wms.masterdata.mdbasics.crud.mapper.ItemDataGlobalMapper;
import com.mushiny.wms.masterdata.mdbasics.domain.ItemData;
import com.mushiny.wms.masterdata.mdbasics.domain.ItemDataGlobal;
import com.mushiny.wms.masterdata.mdbasics.repository.ItemDataGlobalRepository;
import com.mushiny.wms.masterdata.mdbasics.repository.ItemDataRepository;
import com.mushiny.wms.masterdata.mdbasics.service.ItemDataGlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemDataGlobalServiceImpl implements ItemDataGlobalService {

    private final ItemDataGlobalRepository itemDataGlobalRepository;
    private final ItemDataRepository itemDataRepository;
    private final ApplicationContext applicationContext;
    private final ItemDataGlobalMapper itemDataGlobalMapper;

    @Autowired
    public ItemDataGlobalServiceImpl(ItemDataGlobalRepository itemDataGlobalRepository,
                                     ApplicationContext applicationContext,
                                     ItemDataGlobalMapper itemDataGlobalMapper,
                                     ItemDataRepository itemDataRepository) {
        this.itemDataGlobalRepository = itemDataGlobalRepository;
        this.applicationContext = applicationContext;
        this.itemDataGlobalMapper = itemDataGlobalMapper;
        this.itemDataRepository = itemDataRepository;
    }

    @Override
    public ItemDataGlobalDTO create(ItemDataGlobalDTO dto) {
        ItemDataGlobal entity = itemDataGlobalMapper.toEntity(dto);
        boolean useFlag = true;
        while (useFlag) {
            String itemNo = RandomUtil.getItemNo();
            ItemDataGlobal global = itemDataGlobalRepository.getByItemDataNo(itemNo);
            if (global == null) {
                useFlag = false;
                entity.setItemNo(itemNo);
            }
        }
        return itemDataGlobalMapper.toDTO(itemDataGlobalRepository.save(entity));
    }

    @Override
    public void delete(String id) {
        ItemDataGlobal entity = itemDataGlobalRepository.retrieve(id);
        itemDataGlobalRepository.delete(entity);
    }

    @Override
    public ItemDataGlobalDTO update(ItemDataGlobalDTO dto) {
        ItemDataGlobal entity = itemDataGlobalRepository.retrieve(dto.getId());
        itemDataGlobalMapper.updateEntityFromDTO(dto, entity);
        List<ItemData> itemDataList = itemDataRepository.getByItemDataGlobal(entity.getId());
        if (itemDataList != null && !itemDataList.isEmpty()) {
            for (ItemData itemData : itemDataList) {
                itemDataGlobalMapper.updateItemDataFromItemDataGlobal(itemData, entity);
            }
            itemDataRepository.save(itemDataList);
        }
        return itemDataGlobalMapper.toDTO(itemDataGlobalRepository.save(entity));
    }

    @Override
    public ItemDataGlobalDTO retrieve(String id) {
        return itemDataGlobalMapper.toDTO(itemDataGlobalRepository.retrieve(id));
    }

    @Override
    public List<ItemDataGlobalDTO> getBySearchTerm(String searchTerm, Sort sort) {
        List<ItemDataGlobal> entities = itemDataGlobalRepository.getBySearchTerm(searchTerm, sort);
        return itemDataGlobalMapper.toDTOList(entities);
    }

    @Override
    public Page<ItemDataGlobalDTO> getBySearchTerm(String searchTerm, Pageable pageable) {
        Page<ItemDataGlobal> entities = itemDataGlobalRepository.getBySearchTerm(searchTerm, pageable);
        return itemDataGlobalMapper.toDTOPage(pageable, entities);
    }

    @Override
    public List<ItemDataGlobalDTO> getBySkuNo(String skuNo) {
        List<ItemDataGlobal> entities = itemDataGlobalRepository.getBySkuNo(skuNo);
        return itemDataGlobalMapper.toDTOList(entities);
    }

    @Override
    public List<ItemDataGlobalDTO> getByClientId(String clientId) {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "itemNo"));
        applicationContext.isCurrentClient(clientId);
        List<ItemDataGlobal> entities = itemDataGlobalRepository.getList(clientId, sort);
        return itemDataGlobalMapper.toDTOList(entities);
    }
}
