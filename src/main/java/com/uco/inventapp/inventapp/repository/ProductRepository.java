package com.uco.inventapp.inventapp.repository;

import com.uco.inventapp.inventapp.domain.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

    @Repository
    public interface ProductRepository extends CrudRepository<Product, Long> {

        @Query(value = "SELECT c.id as id, c.brand as brand, c.name as name, " +
                "WHERE p.id like %?1%", nativeQuery = true)
        Product findById(long id);

        @Query(value = "SELECT c.id as id, c.brand as brand, c.name as name, " +
                "WHERE p.name like %?1%", nativeQuery = true)
        ArrayList<Product> findByName(String name);

        @Query(value = "SELECT c.id as id, c.brand as brand, c.name as name," +
                " WHERE c.brand like %?1% ", nativeQuery = true)
        ArrayList<Product> findByBrand(String brand);

        @Query(value = "SELECT count(1) FROM users c WHERE c.email = ?1", nativeQuery = true)
        int countByBrand(String brand);


        @Modifying
        @Query(value = "UPDATE users SET brand = ?1, name = ?2 WHERE id = ?5", nativeQuery = true)
        void updateById(String brand, String name, long id);
    }

