package com.uco.inventapp.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.uco.inventapp.domain.Inventory;
import com.uco.inventapp.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/prueba")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @GetMapping("/inventory")
    public ArrayList<Inventory> get(@RequestParam(required = true) int fk_client){
        return  inventoryService.getByFk_client(fk_client);
    }

    @GetMapping("/inventory/all")
    public ArrayList<Inventory> get() {
        return  inventoryService.findAll();
    }

    @PostMapping("/inventory")
    @ResponseStatus(HttpStatus.CREATED)
    public  Inventory create(@RequestBody Inventory inventory){
        return  inventoryService.save(inventory);
    }

    @PutMapping("/inventory/{id}")
    public  void  update(@PathVariable("id") int id,
                         @Valid @RequestBody Inventory inventory){
        inventoryService.update(id,inventory);
    }

    @PatchMapping(value = "/inventory/{id}", consumes ="application/json-patch+json")
    public ResponseEntity<Inventory> patch(@PathVariable("id") long id, @RequestBody JsonPatch patch){
        return  new ResponseEntity<>(inventoryService.patch(id,patch),HttpStatus.OK);
    }

    @DeleteMapping("/inventory/{id}")
    public  void delete(@PathVariable("id") Long id) {
        inventoryService.delete(id);
    }
}
