package com.uco.inventapp.service;

import com.uco.inventapp.domain.Product;
import com.uco.inventapp.repository.ProductRepository;
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
    public Product getByBrand (String brand){
        return productRepository.findByBrand(brand);
    }

}
