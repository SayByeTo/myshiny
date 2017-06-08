package com.mushiny.wms.masterdata.obbasics.service.impl;

import com.mushiny.wms.common.Constant;
import com.mushiny.wms.masterdata.general.crud.mapper.UserMapper;
import com.mushiny.wms.masterdata.general.repository.UserRepository;
import com.mushiny.wms.masterdata.mdbasics.domain.Area;
import com.mushiny.wms.masterdata.obbasics.crud.dto.BoxTypeDTO;
import com.mushiny.wms.masterdata.obbasics.crud.dto.PickPackCellTypeDTO;
import com.mushiny.wms.masterdata.obbasics.crud.dto.ReBinCellTypeDTO;
import com.mushiny.wms.masterdata.obbasics.crud.mapper.BoxTypeMapper;
import com.mushiny.wms.masterdata.obbasics.crud.mapper.PickPackCellTypeMapper;
import com.mushiny.wms.masterdata.obbasics.crud.mapper.ReBinCellTypeMapper;
import com.mushiny.wms.masterdata.obbasics.domain.BoxType;
import com.mushiny.wms.masterdata.obbasics.domain.PickPackCell;
import com.mushiny.wms.masterdata.obbasics.domain.PickPackCellType;
import com.mushiny.wms.masterdata.obbasics.domain.ReBinCellType;
import com.mushiny.wms.masterdata.obbasics.repository.BoxTypeRepository;
import com.mushiny.wms.masterdata.obbasics.repository.PickPackCellTypeRepository;
import com.mushiny.wms.masterdata.obbasics.repository.ReBinCellTypeRepository;
import com.mushiny.wms.masterdata.obbasics.service.PickPackCellTypeObBoxTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PickPackCellTypeObBoxTypeServiceImpl implements PickPackCellTypeObBoxTypeService {

    private final UserRepository userRepository;
    private final PickPackCellTypeMapper pickPackCellTypeMapper;
    private final PickPackCellTypeRepository pickPackCellTypeRepository;
    private final BoxTypeRepository boxTypeRepository;
    private final BoxTypeMapper boxTypeMapper;
    private final UserMapper userMapper;

    @Autowired
    public PickPackCellTypeObBoxTypeServiceImpl(UserRepository userRepository,
                                                PickPackCellTypeMapper pickPackCellTypeMapper,
                                                PickPackCellTypeRepository pickPackCellTypeRepository,
                                                BoxTypeRepository boxTypeRepository,
                                                BoxTypeMapper boxTypeMapper,
                                                UserMapper userMapper) {
        this.userRepository = userRepository;
        this.pickPackCellTypeMapper = pickPackCellTypeMapper;
        this.pickPackCellTypeRepository = pickPackCellTypeRepository;
        this.boxTypeRepository = boxTypeRepository;
        this.boxTypeMapper = boxTypeMapper;
        this.userMapper = userMapper;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void createPickPackCellTypeObBoxType(String boxTypeId, List<String> cellTypes) {
        BoxType boxType = boxTypeRepository.retrieve(boxTypeId);
        List<PickPackCellType> pickPackCellTypes = new ArrayList<>();
        if (cellTypes != null && !cellTypes.isEmpty()) {
            for (String cellTypeId : cellTypes) {
                PickPackCellType types = pickPackCellTypeRepository.retrieve(cellTypeId);
                pickPackCellTypes.add(types);
            }
            boxType.setPickPackCellTypes(pickPackCellTypes);
        } else {
            boxType.setPickPackCellTypes(null);
        }
        boxTypeRepository.save(boxType);
    }

    @Override
    public List<BoxTypeDTO> getList(String clientId) {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "name"));
        List<BoxType> entities = boxTypeRepository.getList(clientId, sort);
        return boxTypeMapper.toDTOList(entities);
    }

    @Override
    public List<PickPackCellTypeDTO> getAssignedUserByReBinCellTypeId(String boxTypeId) {
        BoxType boxType = boxTypeRepository.retrieve(boxTypeId);
        List<PickPackCellType> entities = new ArrayList<>();
        entities.addAll(boxType.getPickPackCellTypes());
        return pickPackCellTypeMapper.toDTOList(entities);
    }

    @Override
    public List<PickPackCellTypeDTO> getUnassignedUserByReBinCellTypeId(String boxTypeId) {
        BoxType boxType = boxTypeRepository.retrieve(boxTypeId);
        List<PickPackCellType> entities = pickPackCellTypeRepository.getUnassignedReBinCellTypes(boxType.getId(), Constant.NOT_LOCKED);
        return pickPackCellTypeMapper.toDTOList(entities);
    }
}