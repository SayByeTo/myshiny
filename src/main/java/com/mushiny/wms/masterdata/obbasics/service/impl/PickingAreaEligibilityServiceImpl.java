package com.mushiny.wms.masterdata.obbasics.service.impl;

import com.mushiny.wms.common.Constant;
import com.mushiny.wms.masterdata.obbasics.domain.PickingArea;
import com.mushiny.wms.masterdata.obbasics.repository.PickingAreaRepository;
import com.mushiny.wms.masterdata.obbasics.service.PickingAreaEligibilityService;
import com.mushiny.wms.masterdata.general.crud.dto.UserDTO;
import com.mushiny.wms.masterdata.general.crud.mapper.UserMapper;
import com.mushiny.wms.masterdata.general.domain.User;
import com.mushiny.wms.masterdata.general.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class PickingAreaEligibilityServiceImpl implements PickingAreaEligibilityService {

    private final UserRepository userRepository;
    private final PickingAreaRepository pickingAreaRepository;
    private final UserMapper userMapper;

    @Autowired
    public PickingAreaEligibilityServiceImpl(UserRepository userRepository,
                                             PickingAreaRepository pickingAreaRepository,
                                             UserMapper userMapper) {
        this.userRepository = userRepository;
        this.pickingAreaRepository = pickingAreaRepository;
        this.userMapper = userMapper;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void createPickingAreaEligibility(String pickingAreaId, List<String> userIds) {
        PickingArea pickingArea = pickingAreaRepository.retrieve(pickingAreaId);
        List<User> users = new ArrayList<>();
        if (userIds != null && !userIds.isEmpty()) {
            for (String userId : userIds) {
                User user = userRepository.retrieve(userId);
                users.add(user);
            }
            pickingArea.setUsers(users);
        } else {
            pickingArea.setUsers(null);
        }
        pickingAreaRepository.save(pickingArea);
    }

    @Override
    public List<UserDTO> getAssignedUserByPickingAreaId(String pickingAreaId) {
        PickingArea pickingArea = pickingAreaRepository.retrieve(pickingAreaId);
        List<User> entities = new ArrayList<>();
        entities.addAll(pickingArea.getUsers());
        return userMapper.toDTOList(entities);
    }

    @Override
    public List<UserDTO> getUnassignedUserByPickingAreaId(String pickingAreaId) {
        PickingArea pickingArea = pickingAreaRepository.retrieve(pickingAreaId);
        List<User> entities = userRepository.getUnassignedPickingAreaUsers(
                pickingArea.getWarehouseId(), pickingArea.getId(), Constant.NOT_LOCKED);
        return userMapper.toDTOList(entities);
    }
}
