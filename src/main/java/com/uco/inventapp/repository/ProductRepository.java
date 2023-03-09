package com.uco.inventapp.inventapp.repository;

import com.uco.inventapp.inventapp.domain.Client;
import com.uco.inventapp.inventapp.domain.Product;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

public interface ProductRepository {
    @Repository
    public interface ClientRepository extends CrudRepository<Client, Long> {

        @Query(value = "SELECT c.id as id, c.brand as brand, c.name as name, " +
                "WHERE p.name like %?1%", nativeQuery = true)
        ArrayList<Product> findByName(String name);

        @Query(value = "SELECT c.id as id, c.brand as brand, c.name as name," +
                " c.password as password FROM users c WHERE c.email = ?1", nativeQuery = true)
        Client findByEmail(String email);

        @Query(value = "SELECT count(1) FROM users c WHERE c.email = ?1", nativeQuery = true)
        int countByEmail(String email);

        @Modifying
        @Query(value = "UPDATE users SET first_name = ?1, last_name = ?2, email = ?3, password = ?4 WHERE id = ?5", nativeQuery = true)
        void updateById(String first_name, String last_name, String email, String password, long id);
    }
}
