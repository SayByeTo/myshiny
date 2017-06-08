package com.mushiny.wms.masterdata.ibbasics.service.impl;

import com.mushiny.wms.masterdata.ibbasics.crud.dto.GoodsReceiptDTO;
import com.mushiny.wms.masterdata.ibbasics.crud.mapper.GoodsReceiptMapper;
import com.mushiny.wms.masterdata.ibbasics.crud.mapper.GoodsReceiptPositionMapper;
import com.mushiny.wms.masterdata.ibbasics.domain.GoodsReceipt;
import com.mushiny.wms.masterdata.ibbasics.repository.GoodsReceiptRepository;
import com.mushiny.wms.masterdata.ibbasics.service.GoodsReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(readOnly = true)
public class GoodsReceiptServiceImpl implements GoodsReceiptService {

    private final GoodsReceiptRepository goodsReceiptRepository;
    private final GoodsReceiptMapper goodsReceiptMapper;
    private final GoodsReceiptPositionMapper goodsReceiptPositionMapper;

    @Autowired
    public GoodsReceiptServiceImpl(GoodsReceiptRepository goodsReceiptRepository,
                                   GoodsReceiptMapper goodsReceiptMapper,
                                   GoodsReceiptPositionMapper goodsReceiptPositionMapper) {
        this.goodsReceiptRepository = goodsReceiptRepository;
        this.goodsReceiptMapper = goodsReceiptMapper;
        this.goodsReceiptPositionMapper = goodsReceiptPositionMapper;
    }

    @Override
    public GoodsReceiptDTO retrieve(String id) {
        GoodsReceipt entity = goodsReceiptRepository.retrieve(id);
        GoodsReceiptDTO dto = goodsReceiptMapper.toDTO(entity);
        dto.setPositions(goodsReceiptPositionMapper.toDTOList(entity.getPositions()));
        return dto;
    }

    @Override
    public Page<GoodsReceiptDTO> getBySearchTerm(String searchTerm, Pageable pageable) {
        Page<GoodsReceipt> entities = goodsReceiptRepository.getBySearchTerm(searchTerm, pageable);
        return goodsReceiptMapper.toDTOPage(pageable, entities);
    }
}
