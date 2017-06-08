package com.mushiny.wms.masterdata.obbasics.web;

import com.mushiny.wms.masterdata.obbasics.crud.dto.BoxTypeDTO;
import com.mushiny.wms.masterdata.obbasics.crud.dto.PickPackCellTypeDTO;
import com.mushiny.wms.masterdata.obbasics.service.PickPackCellTypeObBoxTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PickPackCellTypeObBoxTypeController {

    private final PickPackCellTypeObBoxTypeService pickPackCellTypeObBoxTypeService;

    @Autowired
    public PickPackCellTypeObBoxTypeController(PickPackCellTypeObBoxTypeService pickPackCellTypeObBoxTypeService) {
        this.pickPackCellTypeObBoxTypeService = pickPackCellTypeObBoxTypeService;
    }

    @RequestMapping(value = "/masterdata/outbound/box-types/{id}/pick-pack-cell-types",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createPickPackCellTypeObBoxType(@PathVariable String id,
                                                             @RequestBody List<String> users) {
        pickPackCellTypeObBoxTypeService.createPickPackCellTypeObBoxType(id, users);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/masterdata/outbound/pick-pack-cell-type/box-types",
            method = RequestMethod.GET,
            params = {"clientId"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<BoxTypeDTO>> getList(@RequestParam String clientId) {
        return ResponseEntity.ok(pickPackCellTypeObBoxTypeService.getList(clientId));
    }

    @RequestMapping(value = "/masterdata/outbound/box-types/{id}/pick-pack-cell-types/assigned",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PickPackCellTypeDTO>> getAssignedUserByReBinCellTypeId(@PathVariable String id) {
        return ResponseEntity.ok(pickPackCellTypeObBoxTypeService.getAssignedUserByReBinCellTypeId(id));
    }

    @RequestMapping(value = "/masterdata/outbound/box-types/{id}/pick-pack-cell-types/unassigned",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PickPackCellTypeDTO>> getUnassignedUserByReBinCellTypeId(@PathVariable String id) {
        return ResponseEntity.ok(pickPackCellTypeObBoxTypeService.getUnassignedUserByReBinCellTypeId(id));
    }
}
