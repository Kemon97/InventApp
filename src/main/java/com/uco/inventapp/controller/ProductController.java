package com.uco.inventapp.controller;

import com.uco.inventapp.domain.Product;
import com.uco.inventapp.service.ProductService;
import com.github.fge.jsonpatch.JsonPatch;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

import java.util.ArrayList;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("api/v1")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public ArrayList<Product> get(@RequestParam(required = true) String name) {

        return productService.get(name);
    }

    @GetMapping("/client/brand")
    public Product getByBrand(@RequestParam(required = true) String brand) {

        return productService.getByBrand(brand);
    }

    @GetMapping("/product/all")
    public ArrayList<Product> get() {
        return productService.findAll();
    }

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product product) {
        return productService.save(product);
    }

    @PutMapping("/product/{id}")
    public void update(@PathVariable("id") Long id,
                       @Valid @RequestBody Product product) {
        productService.update(id, product);
    }

    @PutMapping("product/{id}/quantity")
    public ResponseEntity<?> updateProductQuantity(@PathVariable Long id, @RequestBody Map<String, Integer> quantityMap) {
        Integer newQuantity = quantityMap.get("quantity");
        if (newQuantity != null) {
            try {
                Product product = productService.updateProductQuantity(id, newQuantity);
                return ResponseEntity.ok(product);
            } catch (RuntimeException e) {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping(value = "/product/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Product> patch(@PathVariable("id") Long id, @RequestBody JsonPatch patch) {
        return new ResponseEntity<>(productService.patch(id, patch), HttpStatus.OK);
    }

    @DeleteMapping("/product/{id}")
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }
}
