package com.mushiny.wms.masterdata.obbasics.web;

import com.mushiny.wms.masterdata.obbasics.crud.dto.PickingCateGoryRuleDTO;
import com.mushiny.wms.masterdata.obbasics.service.PickingCateGoryRuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/masterdata/outbound/picking-cate-gory-rules")
public class PickingCateGoryRuleController {

    private final PickingCateGoryRuleService pickingCateGoryRuleService;

    @Autowired
    public PickingCateGoryRuleController(PickingCateGoryRuleService pickingCateGoryRuleService) {
        this.pickingCateGoryRuleService = pickingCateGoryRuleService;
    }

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PickingCateGoryRuleDTO> create(@RequestBody PickingCateGoryRuleDTO dto) {
        return ResponseEntity.ok(pickingCateGoryRuleService.create(dto));
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        pickingCateGoryRuleService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PickingCateGoryRuleDTO> update(@RequestBody PickingCateGoryRuleDTO dto) {
        return ResponseEntity.ok(pickingCateGoryRuleService.update(dto));
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PickingCateGoryRuleDTO> get(@PathVariable String id) {
        return ResponseEntity.ok(pickingCateGoryRuleService.retrieve(id));
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PickingCateGoryRuleDTO>> getAll() {
        return ResponseEntity.ok(pickingCateGoryRuleService.getAll());
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            params = {"search"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PickingCateGoryRuleDTO>> getByCriteria(@RequestParam String search, Sort sort) {
        return ResponseEntity.ok(pickingCateGoryRuleService.getBySearchTerm(search, sort));
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            params = {"page", "size"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PickingCateGoryRuleDTO>> getBySearchTerm(
            @RequestParam(required = false) String search, Pageable pageable) {
        return ResponseEntity.ok(pickingCateGoryRuleService.getBySearchTerm(search, pageable));
    }
}
