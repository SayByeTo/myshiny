package com.mushiny.wms.masterdata.mdbasics.service.impl;

import com.mushiny.wms.common.context.ApplicationContext;
import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.masterdata.mdbasics.crud.dto.RobotDTO;
import com.mushiny.wms.masterdata.mdbasics.crud.mapper.RobotMapper;
import com.mushiny.wms.masterdata.mdbasics.domain.Robot;
import com.mushiny.wms.masterdata.mdbasics.exception.MasterDataException;
import com.mushiny.wms.masterdata.mdbasics.repository.RobotRepository;
import com.mushiny.wms.masterdata.mdbasics.service.RobotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RobotServiceImpl implements RobotService {

    private final RobotRepository robotRepository;
    private final ApplicationContext applicationContext;
    private final RobotMapper robotMapper;

    @Autowired
    public RobotServiceImpl(RobotRepository robotRepository,
                            ApplicationContext applicationContext,
                            RobotMapper robotMapper) {
        this.robotRepository = robotRepository;
        this.applicationContext = applicationContext;
        this.robotMapper = robotMapper;
    }

    @Override
    public RobotDTO create(RobotDTO dto) {
        Robot entity = robotMapper.toEntity(dto);
        checkRobotName(entity.getWarehouseId(), entity.getRobot());
        return robotMapper.toDTO(robotRepository.save(entity));
    }

    @Override
    public void delete(String id) {
        Robot entity = robotRepository.retrieve(id);
        robotRepository.delete(entity);
    }

    @Override
    public RobotDTO update(RobotDTO dto) {
        Robot entity = robotRepository.retrieve(dto.getId());
        if (!(entity.getRobot().equalsIgnoreCase(dto.getRobot()))) {
            checkRobotName(entity.getWarehouseId(), dto.getRobot());
        }
        robotMapper.updateEntityFromDTO(dto, entity);
        return robotMapper.toDTO(robotRepository.save(entity));
    }

    @Override
    public RobotDTO retrieve(String id) {
        return robotMapper.toDTO(robotRepository.retrieve(id));
    }

    @Override
    public List<RobotDTO> getBySearchTerm(String searchTerm, Sort sort) {
        List<Robot> entities = robotRepository.getBySearchTerm(searchTerm, sort);
        return robotMapper.toDTOList(entities);
    }

    @Override
    public Page<RobotDTO> getBySearchTerm(String searchTerm, Pageable pageable) {
        Page<Robot> entities = robotRepository.getBySearchTerm(searchTerm, pageable);
        return robotMapper.toDTOPage(pageable, entities);
    }

    @Override
    public void enter(String robotId, String password) {
        checkEnter(robotId, password);
        return;
    }

    private void checkRobotName(String warehouse, String robotId) {
        Robot robot = robotRepository.getByName(warehouse, robotId);
        if (robot != null) {
            throw new ApiException(MasterDataException.EX_MD_ROBOT_NAME_UNIQUE.toString(), robotId);
        }
    }

    private void checkEnter(String robotId, String password) {
        Robot robot = robotRepository.getByEnter(robotId, password);
        if (robot == null) {
            throw new ApiException(MasterDataException.EX_MD_ROBOT_ENTER_ERROR.toString(), robotId);
        }
    }

}
