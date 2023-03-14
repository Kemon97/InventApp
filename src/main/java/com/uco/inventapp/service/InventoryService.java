package com.uco.inventapp.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.uco.inventapp.domain.Inventory;
import com.uco.inventapp.repository.InventoryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class InventoryService {

    @Autowired
    private  InventoryRepository inventoryRepository;

    @Transactional
    public  ArrayList<Inventory> findAll() { return (ArrayList<Inventory>) inventoryRepository.findAll();}

    @Transactional
    public ArrayList<Inventory> getByFk_client(int fk_client) { return inventoryRepository.findByFk_client(fk_client);}

    @Transactional
    public  ArrayList<Inventory> getByFk_product(int fk_product) { return  inventoryRepository.findByFk_product(fk_product);}

    @Transactional
    public  Inventory Save(Inventory inventory){
        if(inventoryRepository.countByProductoCliente(inventory.getFk_client(), inventory.getFk_product())>0){
            throw new IllegalArgumentException("product already exists for customer in inventory");
        }
        return  inventoryRepository.save(inventory);
    }

    @Transactional
    public  void update(int id,Inventory inventoryDomain){
        if(inventoryRepository.findById(id).isEmpty()) throw new EntityNotFoundException();
        inventoryRepository.updateById(inventoryDomain.getFk_client(),inventoryDomain.getFk_product(),inventoryDomain.get_State(),id);
        inventoryRepository.updateByIdState(inventoryDomain.get_State(),id);
    }

    @Transactional
   public void delete(Long id){ inventoryRepository.deleteById(id);}

   /* @Transactional
    public  Inventory patch(Long id, JsonPatch patch){
        return InventoryRepository.save(
                applyPatchToInventory(patch, inventoryRepository.findById(id).orElseThrow(EntityNotFoundException::new))
        );

    }*/

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