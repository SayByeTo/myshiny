package com.mushiny.wms.masterdata.mdbasics.business;

import com.mushiny.wms.common.context.ApplicationContext;
import com.mushiny.wms.common.exception.ApiException;
import com.mushiny.wms.common.exception.ExceptionEnum;
import com.mushiny.wms.common.utils.LevelUtil;
import com.mushiny.wms.masterdata.mdbasics.business.dto.PodStorageLocationsDTO;
import com.mushiny.wms.masterdata.mdbasics.domain.*;
import com.mushiny.wms.masterdata.mdbasics.exception.MasterDataException;
import com.mushiny.wms.masterdata.mdbasics.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class PodBusiness {

    private final PodRepository podRepository;
    private final PodTypeRepository podTypeRepository;
    private final PodTypePositionRepository podTypePositionRepository;
    private final ZoneRepository zoneRepository;
    private final AreaRepository areaRepository;
    private final StorageLocationRepository storageLocationRepository;
    private final ApplicationContext applicationContext;

    @Autowired
    public PodBusiness(ApplicationContext applicationContext,
                       PodRepository podRepository,
                       PodTypeRepository podTypeRepository,
                       PodTypePositionRepository podTypePositionRepository,
                       ZoneRepository zoneRepository,
                       AreaRepository areaRepository,
                       StorageLocationRepository storageLocationRepository) {
        this.applicationContext = applicationContext;
        this.podRepository = podRepository;
        this.podTypeRepository = podTypeRepository;
        this.podTypePositionRepository = podTypePositionRepository;
        this.zoneRepository = zoneRepository;
        this.areaRepository = areaRepository;
        this.storageLocationRepository = storageLocationRepository;
    }

    public void createMore(PodStorageLocationsDTO dto) {
        // 验证传入的参数
        String warehouse = applicationContext.getCurrentWarehouse();
        String client = dto.getClientId();
        Zone zone = zoneRepository.retrieve(dto.getZoneId());
        PodType podType = podTypeRepository.retrieve(dto.getPodTypeId());
        Area area = areaRepository.retrieve(dto.getAreaId());
        if (!client.equalsIgnoreCase(zone.getClientId())
                || !client.equalsIgnoreCase(area.getClientId())) {
            throw new ApiException(MasterDataException.EX_MD_POD_CLIENT_ERROR.toString(), dto.getClientId());
        }
        int fromBay = dto.getFromPod();
        int toBay = dto.getToPod();
        // 取出PodType下所有PodTypePosition
        List<PodTypePosition> podTypePositions = podTypePositionRepository.getByPodType(podType);
        // 取pod的最大index
        int maxPodIndex = podRepository.getMaxIndexPod(warehouse);
        // 生成Pod
        for (int j = fromBay; j <= toBay; j++) {
            maxPodIndex++;
            String podName = "P" + String.format("%07d", maxPodIndex);
            Pod pod = new Pod();
            pod.setPodType(podType);
            pod.setName(podName);
            pod.setZone(zone);
            pod.setPodIndex(maxPodIndex);
            pod.setClientId(client);
            pod.setWarehouseId(warehouse);
            checkPodName(warehouse, podName);
            pod = podRepository.save(pod);
            // 生成Pod下的StorageLocation
            int binIndexA = 0;
            int binIndexB = 0;
            int binIndexC = 0;
            int binIndexD = 0;
            for (PodTypePosition podTypePosition : podTypePositions) {
                for (int c = 1; c <= podTypePosition.getNumberOfColumns(); c++) {
                    StorageLocation storageLocation = new StorageLocation();
                    String locationName = pod.getName() + podTypePosition.getFace() +
                            LevelUtil.getLevel(podTypePosition.getLevel()) + String.format("%02d", c);
                    storageLocation.setName(locationName);
                    storageLocation.setStorageLocationType(podTypePosition.getStorageLocationType());
                    storageLocation.setArea(area);
                    storageLocation.setZone(zone);
                    storageLocation.setDropZone(podTypePosition.getDropZone());
                    storageLocation.setPod(pod);
                    storageLocation.setxPos(podTypePosition.getLevel());
                    storageLocation.setyPos(c);
                    storageLocation.setzPos(podTypePosition.getStorageLocationType().getDepth().intValue());
                    storageLocation.setAllocation(BigDecimal.ZERO);
                    storageLocation.setAllocationState(0);
                    storageLocation.setFace(podTypePosition.getFace());
                    storageLocation.setColor(podTypePosition.getColor());
                    int binIndex = 0;
                    switch (podTypePosition.getFace()){
                        case "A" : binIndex = ++binIndexA;
                            break;
                        case "B" : binIndex = ++binIndexB;
                            break;
                        case "C" : binIndex = ++binIndexC;
                            break;
                        case "D" : binIndex = ++binIndexD;
                            break;
                        default: throw new ApiException(MasterDataException.EX_MD_POD_FACE_ERROR.toString(),podTypePosition.getFace());
                    }
                    storageLocation.setOrderIndex(binIndex);
                    storageLocation.setClientId(client);
                    storageLocation.setWarehouseId(warehouse);
                    storageLocationRepository.save(storageLocation);
                }
            }
        }
    }

    private void checkPodName(String warehouse, String podName) {
        Pod pod = podRepository.getByName(warehouse, podName);
        if (pod != null) {
            throw new ApiException(MasterDataException.EX_MD_POD_NAME_UNIQUE.toString(), podName);
        }
    }
}
