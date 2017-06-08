package com.mushiny.wms.masterdata.obbasics.service.impl;

import com.mushiny.wms.common.context.ApplicationContext;
import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.common.utils.LevelUtil;
import com.mushiny.wms.masterdata.obbasics.crud.dto.PickPackWallDTO;
import com.mushiny.wms.masterdata.obbasics.crud.mapper.PickPackWallMapper;
import com.mushiny.wms.masterdata.obbasics.domain.PickPackCell;
import com.mushiny.wms.masterdata.obbasics.domain.PickPackWall;
import com.mushiny.wms.masterdata.obbasics.domain.PickPackWallType;
import com.mushiny.wms.masterdata.obbasics.domain.PickPackWallTypePosition;
import com.mushiny.wms.masterdata.obbasics.exception.OutBoundException;
import com.mushiny.wms.masterdata.obbasics.repository.*;
import com.mushiny.wms.masterdata.obbasics.service.PickPackWallService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PickPackWallServiceImpl implements PickPackWallService {

    private final PickPackWallRepository pickPackWallRepository;
    private final ApplicationContext applicationContext;
    private final PickPackWallMapper pickPackWallMapper;
    private final PickPackWallTypeRepository pickPackWallTypeRepository;
    private final PickPackWallTypePositionRepository pickPackWallTypePositionRepository;
    private final PickPackCellRepository pickPackCellRepository;
    private final DigitalLabelRepository digitalLabelRepository;

    @Autowired
    public PickPackWallServiceImpl(PickPackWallRepository pickPackWallRepository,
                                   ApplicationContext applicationContext,
                                   PickPackWallMapper pickPackWallMapper,
                                   PickPackWallTypeRepository pickPackWallTypeRepository,
                                   PickPackWallTypePositionRepository pickPackWallTypePositionRepository,
                                   PickPackCellRepository pickPackCellRepository,
                                   DigitalLabelRepository digitalLabelRepository) {
        this.pickPackWallRepository = pickPackWallRepository;
        this.applicationContext = applicationContext;
        this.pickPackWallMapper = pickPackWallMapper;
        this.pickPackWallTypeRepository = pickPackWallTypeRepository;
        this.pickPackWallTypePositionRepository = pickPackWallTypePositionRepository;
        this.pickPackCellRepository = pickPackCellRepository;
        this.digitalLabelRepository = digitalLabelRepository;
    }

    @Override
    public PickPackWallDTO create(PickPackWallDTO dto) {
        PickPackWall entity = pickPackWallMapper.toEntity(dto);
        checkName(entity.getWarehouseId(), entity.getName());
        return pickPackWallMapper.toDTO(pickPackWallRepository.save(entity));
    }

    @Override
    public void createMore(PickPackWallDTO dto) {
        PickPackWall entity = pickPackWallMapper.toEntity(dto);
        checkName(entity.getWarehouseId(), entity.getName());
        PickPackWallType pickPackWallType = pickPackWallTypeRepository.retrieve(dto.getTypeId());

        PickPackWall pickPackWall = new PickPackWall();
        pickPackWall.setName(dto.getName());
        pickPackWall.setDescription(dto.getDescription());
        pickPackWall.setNumberOfRows(dto.getNumberOfRows());
        pickPackWall.setNumberOfColumns(dto.getNumberOfColumns());
        pickPackWall.setPickPackWallType(pickPackWallTypeRepository.retrieve(dto.getTypeId()));
        pickPackWall.setWarehouseId(applicationContext.getCurrentWarehouse());
        pickPackWall = pickPackWallRepository.save(pickPackWall);

        //取前台传来的FieldTypeId
        List<PickPackWallTypePosition> pickPackWallTypePositions =
                pickPackWallTypePositionRepository.getByFileType(dto.getPickPackFieldTypeNames());
        if(pickPackWallTypePositions == null) {
            throw new ApiException(OutBoundException.EX_MD_OB_DATA_NOT_FOUND.toString(), "");
        }

        //初始digitalLabel的List为0然后循环加一取下一个对象
        int listSize = 0;

        for(PickPackWallTypePosition pickPackWallTypePosition : pickPackWallTypePositions) {
            int orderIndex = 1;
            //取FieldType下行、列进行循环生成X、Y坐标
            int columns = pickPackWallTypePosition.getPickPackFieldType().getNumberOfColumns();
            int rows = pickPackWallTypePosition.getPickPackFieldType().getNumberOfRows();
//            for(int y = 1; y <= columns; y++) {
            for(int x = 1; x <= rows; x++) {
                for(int y = 1; y <= columns; y++) {
                    PickPackCell pickPackCell = new PickPackCell();
                    pickPackCell.setName(dto.getName() + "-" + LevelUtil.getLevel(x) +  String.format("%02d", y));
                    pickPackCell.setPickPackCellType(pickPackWallTypePosition.getPickPackFieldType().
                            getPickPackCellType());
                    pickPackCell.setxPos(x);
                    pickPackCell.setyPos(y);
                    pickPackCell.setzPos(1);
                    pickPackCell.setField(pickPackWallTypePosition.getPickPackFieldType().getName());
                    pickPackCell.setFieldIndex(pickPackWallTypePosition.getPickPackFieldType().getFieldIndex());

                    if(dto.getDigitalLabel1().get(listSize) != null) {
                        pickPackCell.setDigitalLabel1(digitalLabelRepository.retrieve(dto.getDigitalLabel1().get(listSize)));
                    } else {
                        pickPackCell.setDigitalLabel1(null);
                    }
                    if(dto.getDigitalLabel2().get(listSize) != null) {
                        pickPackCell.setDigitalLabel2(digitalLabelRepository.retrieve(dto.getDigitalLabel2().get(listSize)));
                    } else {
                        pickPackCell.setDigitalLabel2(null);
                    }

                    pickPackCell.setOrderIndex(orderIndex);
                    listSize++;
                    orderIndex++;
                    pickPackCell.setWarehouseId(applicationContext.getCurrentWarehouse());
                    pickPackCell.setPickPackWall(pickPackWall);
                    pickPackCellRepository.save(pickPackCell);
                }
            }
        }

    }

    @Override
    public void delete(String id) {
        PickPackWall entity = pickPackWallRepository.retrieve(id);
        pickPackWallRepository.delete(entity);
    }

    @Override
    public PickPackWallDTO update(PickPackWallDTO dto) {
        PickPackWall entity = pickPackWallRepository.retrieve(dto.getId());
        if (!(entity.getName().equalsIgnoreCase(dto.getName()))) {
            checkName(entity.getWarehouseId(), dto.getName());
        }
        pickPackWallMapper.updateEntityFromDTO(dto, entity);
        return pickPackWallMapper.toDTO(pickPackWallRepository.save(entity));
    }

    @Override
    public PickPackWallDTO retrieve(String id) {
        return pickPackWallMapper.toDTO(pickPackWallRepository.retrieve(id));
    }

    @Override
    public List<PickPackWallDTO> getAll() {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC, "name"));
        List<PickPackWall> entities = pickPackWallRepository.getList(null, sort);
        return pickPackWallMapper.toDTOList(entities);
    }

    @Override
    public List<PickPackWallDTO> getBySearchTerm(String searchTerm, Sort sort) {
        List<PickPackWall> entities = pickPackWallRepository.getBySearchTerm(searchTerm, sort);
        return pickPackWallMapper.toDTOList(entities);
    }

    @Override
    public Page<PickPackWallDTO> getBySearchTerm(String searchTerm, Pageable pageable) {
        Page<PickPackWall> entities = pickPackWallRepository.getBySearchTerm(searchTerm, pageable);
        return pickPackWallMapper.toDTOPage(pageable, entities);
    }

    private void checkName(String warehouse, String areaName) {
        PickPackWall workStationType = pickPackWallRepository.getByName(warehouse, areaName);
        if (workStationType != null) {
            throw new ApiException(OutBoundException.EX_MD_OB_PICKPACK_WALL_NAME_UNIQUE.toString(), areaName);
        }
    }
}