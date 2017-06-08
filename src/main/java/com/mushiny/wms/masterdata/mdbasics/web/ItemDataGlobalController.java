package com.mushiny.wms.masterdata.mdbasics.web;

import com.mushiny.wms.masterdata.mdbasics.crud.dto.ItemDataGlobalDTO;
import com.mushiny.wms.masterdata.mdbasics.service.ItemDataGlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/masterdata/item-data-globals")
public class ItemDataGlobalController {

    private final ItemDataGlobalService itemDataGlobalService;

    @Autowired
    public ItemDataGlobalController(ItemDataGlobalService itemDataGlobalService) {
        this.itemDataGlobalService = itemDataGlobalService;
    }

    @RequestMapping(value = "",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDataGlobalDTO> create(@RequestBody ItemDataGlobalDTO dto) {
        return ResponseEntity.ok(itemDataGlobalService.create(dto));
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(@PathVariable String id) {
        itemDataGlobalService.delete(id);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "",
            method = RequestMethod.PUT,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDataGlobalDTO> update(@RequestBody ItemDataGlobalDTO dto) {
        return ResponseEntity.ok(itemDataGlobalService.update(dto));
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ItemDataGlobalDTO> get(@PathVariable String id) {
        return ResponseEntity.ok(itemDataGlobalService.retrieve(id));
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            params = {"skuNo"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemDataGlobalDTO>> getBySkuNo(@RequestParam String skuNo) {
        return ResponseEntity.ok(itemDataGlobalService.getBySkuNo(skuNo));
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            params = {"clientId"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemDataGlobalDTO>> getByClientId(@RequestParam String clientId) {
        return ResponseEntity.ok(itemDataGlobalService.getByClientId(clientId));
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            params = {"search"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ItemDataGlobalDTO>> getByCriteria(@RequestParam String search, Sort sort) {
        return ResponseEntity.ok(itemDataGlobalService.getBySearchTerm(search, sort));
    }

    @RequestMapping(value = "",
            method = RequestMethod.GET,
            params = {"page", "size"},
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Page<ItemDataGlobalDTO>> getBySearchTerm(
            @RequestParam(required = false) String search, Pageable pageable) {
        return ResponseEntity.ok(itemDataGlobalService.getBySearchTerm(search, pageable));
    }
}
