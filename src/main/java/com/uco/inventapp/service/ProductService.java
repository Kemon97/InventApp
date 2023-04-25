package com.uco.inventapp.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.uco.inventapp.domain.Product;
import com.uco.inventapp.repository.ProductRepository;
import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import com.uco.inventapp.util.MessageSender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Locale;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    private final MessageSender<Product> messageSenderProduct;

    public ProductService(MessageSender<Product> messageSenderProduct) {
        this.messageSenderProduct = messageSenderProduct;
    }

    @Transactional
    public ArrayList<Product> findAll(){
        return (ArrayList<Product>) productRepository.findAll();
    }

    @Transactional
    public ArrayList<Product> get(String name){
        return productRepository.findByName(name.toUpperCase(Locale.ROOT));
    }

    @Transactional
    public Product getByBrand (String brand){
        return productRepository.findByBrand(brand);
    }

    @Transactional
    public Product save(Product product) {
        if (productRepository.countByEmail(product.getName()) > 0) {
            throw new IllegalArgumentException("Product already exits");
        }
        messageSenderProduct.execute(product,product.getBrand().toString());
        return productRepository.save(product);
    }

    @Transactional
    public void update(Long id, Product productDomain) {
        if (productRepository.findById(id).isEmpty()) throw new EntityNotFoundException();
        productRepository.updateById(productDomain.getName(), productDomain.getBrand(), id);
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

    private Product applyPatchToProduct(JsonPatch patch, Product product) {
        try {
            var objectMapper = new ObjectMapper();
            JsonNode patched = patch.apply(objectMapper.convertValue(product, JsonNode.class));
            return objectMapper.treeToValue(patched, Product.class);
        } catch (JsonPatchException | JsonProcessingException e) {
            throw new RuntimeException("Error al aplicar el PATCH en la entidad PRODUCTO");
        }
    }
}

