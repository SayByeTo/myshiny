package com.mushiny.wms.masterdata.obbasics.web;

import com.mushiny.wms.masterdata.obbasics.service.PickingAreaEligibilityService;
import com.mushiny.wms.masterdata.general.crud.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/masterdata/outbound/picking-area-eligibility")
public class PickingAreaEligibilityController {

    private final PickingAreaEligibilityService pickingAreaEligibilityService;

    @Autowired
    public PickingAreaEligibilityController(PickingAreaEligibilityService pickingAreaEligibilityService) {
        this.pickingAreaEligibilityService = pickingAreaEligibilityService;
    }

    @RequestMapping(value = "/picking-areas/{id}/users",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPickingAreaEligibility(@PathVariable String id,
                                                             @RequestBody List<String> users) {
        pickingAreaEligibilityService.createPickingAreaEligibility(id, users);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/picking-areas/{id}/users/assigned",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getAssignedUserByPickingAreaId(@PathVariable String id) {
        return ResponseEntity.ok(pickingAreaEligibilityService.getAssignedUserByPickingAreaId(id));
    }

    @RequestMapping(value = "/picking-areas/{id}/users/unassigned",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserDTO>> getUnassignedUserByPickingAreaId(@PathVariable String id) {
        return ResponseEntity.ok(pickingAreaEligibilityService.getUnassignedUserByPickingAreaId(id));
    }
}
