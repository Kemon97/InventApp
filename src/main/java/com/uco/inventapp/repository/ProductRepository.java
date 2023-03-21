package com.uco.inventapp.repository;

<<<<<<< HEAD:src/main/java/com/uco/inventapp/inventapp/repository/ProductRepository.java
import com.uco.inventapp.inventapp.domain.Product;
=======
import com.uco.inventapp.domain.Product;
>>>>>>> main:src/main/java/com/uco/inventapp/repository/ProductRepository.java
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

    @Repository
    public interface ProductRepository extends CrudRepository<Product, Long> {
<<<<<<< HEAD:src/main/java/com/uco/inventapp/inventapp/repository/ProductRepository.java

        @Query(value = "SELECT c.id as id, c.brand as brand, c.name as name, " +
                "WHERE p.id like %?1%", nativeQuery = true)
        Product findById(long id);
=======
>>>>>>> main:src/main/java/com/uco/inventapp/repository/ProductRepository.java

        @Query(value = "SELECT c.id as id, c.brand as brand, c.name as name, " +
                "WHERE p.name like %?1%", nativeQuery = true)
        ArrayList<Product> findByName(String name);

        @Query(value = "SELECT c.id as id, c.brand as brand, c.name as name," +
<<<<<<< HEAD:src/main/java/com/uco/inventapp/inventapp/repository/ProductRepository.java
                " WHERE c.brand like %?1% ", nativeQuery = true)
        ArrayList<Product> findByBrand(String brand);
=======
                " c.password as password FROM users c WHERE c.email = ?1", nativeQuery = true)
        Product findByBrand(String brand);
>>>>>>> main:src/main/java/com/uco/inventapp/repository/ProductRepository.java

        @Query(value = "SELECT count(1) FROM users c WHERE c.email = ?1", nativeQuery = true)
        int countByBrand(String brand);


        @Modifying
<<<<<<< HEAD:src/main/java/com/uco/inventapp/inventapp/repository/ProductRepository.java
        @Query(value = "UPDATE users SET brand = ?1, name = ?2 WHERE id = ?5", nativeQuery = true)
        void updateById(String brand, String name, long id);
=======
        @Query(value = "UPDATE users SET first_name = ?1, last_name = ?2, email = ?3, password = ?4 WHERE id = ?5", nativeQuery = true)
        void updateById(String name, String brand, long id);
>>>>>>> main:src/main/java/com/uco/inventapp/repository/ProductRepository.java
    }

