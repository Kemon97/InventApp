<<<<<<< HEAD:src/main/java/com/uco/inventapp/inventapp/controller/ProductController.java
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
=======
package com.uco.inventapp.controller;

import com.uco.inventapp.domain.Product;
import com.uco.inventapp.service.ProductService;
import com.github.fge.jsonpatch.JsonPatch;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> main:src/main/java/com/uco/inventapp/controller/ProductController.java
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

<<<<<<< HEAD:src/main/java/com/uco/inventapp/inventapp/controller/ProductController.java
=======
import java.util.ArrayList;

>>>>>>> main:src/main/java/com/uco/inventapp/controller/ProductController.java
@RestController
@RequestMapping("api/v1")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("/product")
    public ArrayList<Product> get(@RequestParam(required = true) String name) {

        return productService.get(name);
    }

<<<<<<< HEAD:src/main/java/com/uco/inventapp/inventapp/controller/ProductController.java
    @GetMapping("/product/brand")
    public ArrayList<Product> getByEmail(@RequestParam(required = true) String brand) {
=======
    @GetMapping("/client/brand")
    public Product getByBrand(@RequestParam(required = true) String brand) {
>>>>>>> main:src/main/java/com/uco/inventapp/controller/ProductController.java

        return productService.getByBrand(brand);
    }

    @GetMapping("/product/all")
    public ArrayList<Product> get() {
        return productService.findAll();
    }

    @PostMapping("/product")
    @ResponseStatus(HttpStatus.CREATED)
<<<<<<< HEAD:src/main/java/com/uco/inventapp/inventapp/controller/ProductController.java
    public Product create(@RequestBody Product object) {
        return productService.save(object);
=======
    public Product create(@RequestBody Product product) {
        return productService.save(product);
>>>>>>> main:src/main/java/com/uco/inventapp/controller/ProductController.java
    }

    @PutMapping("/product/{id}")
    public void update(@PathVariable("id") Long id,
                       @Valid @RequestBody Product Product) {
        productService.update(id, Product);
    }

<<<<<<< HEAD:src/main/java/com/uco/inventapp/inventapp/controller/ProductController.java
    @PatchMapping(value = "/product/{id}")
    public ResponseEntity<Product> patch(@PathVariable ("id") Long id, @RequestBody JsonPatch patch) {
        return new ResponseEntity<>(productService.patch(id, patch), HttpStatus.OK);

=======
    @PatchMapping(value = "/product/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<Product> patch(@PathVariable("id") Long id, @RequestBody JsonPatch patch) {
        return new ResponseEntity<>(productService.patch(id, patch), HttpStatus.OK);
>>>>>>> main:src/main/java/com/uco/inventapp/controller/ProductController.java
    }

    @DeleteMapping("/product/{id}")
    public void delete(@PathVariable("id") Long id) {
        productService.delete(id);
    }
}
