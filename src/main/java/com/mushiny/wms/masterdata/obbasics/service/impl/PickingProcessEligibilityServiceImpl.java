package com.mushiny.wms.masterdata.obbasics.service.impl;

import com.mushiny.wms.common.Constant;
import com.mushiny.wms.masterdata.obbasics.domain.ProcessPath;
import com.mushiny.wms.masterdata.obbasics.repository.ProcessPathRepository;
import com.mushiny.wms.masterdata.obbasics.service.PickingProcessEligibilityService;
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
public class PickingProcessEligibilityServiceImpl implements PickingProcessEligibilityService {

    private final UserRepository userRepository;
    private final ProcessPathRepository processPathRepository;
    private final UserMapper userMapper;

    @Autowired
    public PickingProcessEligibilityServiceImpl(UserRepository userRepository,
                                                ProcessPathRepository processPathRepository,
                                                UserMapper userMapper) {
        this.userRepository = userRepository;
        this.processPathRepository = processPathRepository;
        this.userMapper = userMapper;
    }

    @SuppressWarnings("Duplicates")
    @Override
    public void createPickingAreaEligibility(String processPathId, List<String> userIds) {
        ProcessPath processPath = processPathRepository.retrieve(processPathId);
        List<User> users = new ArrayList<>();
        if (userIds != null && !userIds.isEmpty()) {
            for (String userId : userIds) {
                User user = userRepository.retrieve(userId);
                users.add(user);
            }
            processPath.setUsers(users);
        } else {
            processPath.setUsers(null);
        }
        processPathRepository.save(processPath);
    }

    @Override
    public List<UserDTO> getAssignedUserByProcessPathId(String processPathId) {
        ProcessPath processPath = processPathRepository.retrieve(processPathId);
        List<User> entities = new ArrayList<>();
        entities.addAll(processPath.getUsers());
        return userMapper.toDTOList(entities);
    }

    @Override
    public List<UserDTO> getUnassignedUserByProcessPathId(String processPathId) {
        ProcessPath processPath = processPathRepository.retrieve(processPathId);
        List<User> entities = userRepository.getUnassignedProcessPathUsers(
                processPath.getWarehouseId(), processPath.getId(), Constant.NOT_LOCKED);
        return userMapper.toDTOList(entities);
    }
}
