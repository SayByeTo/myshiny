package com.mushiny.wms.masterdata.obbasics.web;

import com.mushiny.wms.masterdata.obbasics.crud.dto.PickingCateGoryDTO;
import com.mushiny.wms.masterdata.obbasics.service.PickingCateGoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/masterdata/outbound/picking-cate-gories")
public class PickingCateGoryController {

    private final PickingCateGoryService pickingCateGoryService;

    @Autowired
    public PickingCateGoryController(PickingCateGoryService pickingCateGoryService) {
        this.pickingCateGoryService = pickingCateGoryService;
    }

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PickingCateGoryDTO> create(@RequestBody PickingCateGoryDTO dto) {
        return ResponseEntity.ok(pickingCateGoryService.create(dto));
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        pickingCateGoryService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PickingCateGoryDTO> update(@RequestBody PickingCateGoryDTO dto) {
        return ResponseEntity.ok(pickingCateGoryService.update(dto));
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PickingCateGoryDTO> get(@PathVariable String id) {
        return ResponseEntity.ok(pickingCateGoryService.retrieve(id));
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PickingCateGoryDTO>> getAll() {
        return ResponseEntity.ok(pickingCateGoryService.getAll());
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            params = {"search"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PickingCateGoryDTO>> getByCriteria(@RequestParam String search, Sort sort) {
        return ResponseEntity.ok(pickingCateGoryService.getBySearchTerm(search, sort));
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            params = {"page", "size"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<PickingCateGoryDTO>> getBySearchTerm(
            @RequestParam(required = false) String search, Pageable pageable) {
        return ResponseEntity.ok(pickingCateGoryService.getBySearchTerm(search, pageable));
    }
}
