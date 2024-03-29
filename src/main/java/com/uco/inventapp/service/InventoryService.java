package com.uco.inventapp.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.uco.inventapp.domain.Client;
import com.uco.inventapp.domain.Inventory;
import com.uco.inventapp.repository.InventoryRepository;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Locale;

@Service
public class InventoryService {

    @Autowired
    private  InventoryRepository inventoryRepository;

    @Transactional
    public  ArrayList<Inventory> findAll() { return (ArrayList<Inventory>) inventoryRepository.findAll();}

    @Transactional
    public ArrayList<Inventory> get(String fkcli) {
        return inventoryRepository.findByFk_client(fkcli.toUpperCase(Locale.ROOT));
    }

    @Transactional
    public  Inventory getByFk_product(String fk_product) { return  inventoryRepository.findByFk_product(fk_product);}

    @Transactional
    public  Inventory save(Inventory inventory){
        if(inventoryRepository.countByProductoCliente(inventory.getFk_client(), inventory.getFk_product())>0){
            throw new IllegalArgumentException("product already exists for customer in inventory");
        }
        return  inventoryRepository.save(inventory);
    }

    @Transactional
    public  void update(long id,Inventory inventoryDomain){
        if(inventoryRepository.findById(id).isEmpty()) throw new EntityNotFoundException();
        inventoryRepository.updateById(inventoryDomain.getFk_client(),inventoryDomain.getFk_product(),inventoryDomain.get_State(),id);
    }

    @Transactional
   public void delete(Long id){ inventoryRepository.deleteById(id);}

    @Transactional
    public  Inventory patch(Long id, JsonPatch patch){
        return inventoryRepository.save(
                applyPatchToInventory(patch, inventoryRepository.findById(id).orElseThrow(EntityNotFoundException::new))
        );

    }

    private Inventory applyPatchToInventory(JsonPatch patch, Inventory inventory) {
        try {
            var objectMapper = new ObjectMapper();
            JsonNode patched = patch.apply(objectMapper.convertValue(inventory, JsonNode.class));
            return objectMapper.treeToValue(patched, Inventory.class);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}