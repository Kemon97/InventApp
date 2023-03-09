package com.uco.inventapp.inventapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.uco.inventapp.inventapp.domain.Client;
import com.uco.inventapp.inventapp.domain.Product;
import com.uco.inventapp.inventapp.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Locale;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public ArrayList<Product> findAll(){
        return (ArrayList<Product>) productRepository.findAll();
    }

    @Transactional
    public ArrayList<Product> get(String name){
        return productRepository.findByName(name.toUpperCase(Locale.ROOT));
    }

    @Transactional
    public ArrayList<Product> getByBrand (String brand){
        return productRepository.findByBrand(brand);
    }

    @Transactional
    public Product save(Product thing) {
        if (productRepository.countByBrand(thing.getBrand()) > 0) {
            throw new IllegalArgumentException("brand already exits");
        }
        return productRepository.save(thing);
    }

    @Transactional
    public void update(Long id, Product productDomain) {
        if (productRepository.findById(id).isEmpty()) throw new EntityNotFoundException();
        productRepository.updateById(productDomain.getBrand(), productDomain.getName(), id);
    }

    @Transactional
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Transactional
    public Product patch(Long id, JsonPatch patch) {
        return productRepository.save(
                applyPatchToProduct(patch, productRepository.findById(id)
                        .orElseThrow(EntityNotFoundException::new)));
    }

    private Product applyPatchToProduct(JsonPatch patch, Product thing) {
        try {
            var objectMapper = new ObjectMapper();
            JsonNode patched = patch.apply(objectMapper.convertValue(thing, JsonNode.class));
            return objectMapper.treeToValue(patched, Product.class);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
