package com.uco.inventapp.inventapp.service;

import com.uco.inventapp.inventapp.domain.Product;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Locale;

@Service
public class ProductService {

    @Autowired
    private ProductService productService;

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

}
