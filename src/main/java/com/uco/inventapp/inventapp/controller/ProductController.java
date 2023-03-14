package com.uco.inventapp.inventapp.controller;

import com.github.fge.jsonpatch.JsonPatch;
import com.uco.inventapp.inventapp.domain.Client;
import com.uco.inventapp.inventapp.domain.Product;
import com.uco.inventapp.inventapp.repository.ProductRepository;
import com.uco.inventapp.inventapp.service.ClientService;
import com.uco.inventapp.inventapp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public ArrayList<Product> get(@RequestParam(required = true) String name) {

        return productService.get(name);
    }

    @GetMapping("/product/brand")
    public ArrayList<Product> getByEmail(@RequestParam(required = true) String brand) {

        return productService.getByBrand(brand);
    }

    @GetMapping("/product/all")
    public ArrayList<Product> get() {
        return productService.findAll();
    }

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
    public Product create(@RequestBody Product object) {
        return productService.save(object);
    }

    @PutMapping("/product/{id}")
    public void update(@PathVariable("id") Long id,
                       @Valid @RequestBody Product Product) {
        productService.update(id, Product);
    }

    @PatchMapping(value = "/product/{id}")
    public ResponseEntity<Product> patch(@PathVariable ("id") Long id, @RequestBody JsonPatch patch) {
        return new ResponseEntity<>(productService.patch(id, patch), HttpStatus.OK);

    }

    @DeleteMapping("/product/{id}")
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }
}
