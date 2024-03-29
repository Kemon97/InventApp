package com.uco.inventapp.repository;

import com.uco.inventapp.domain.Client;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface ClientRepository extends CrudRepository<Client, Long> {

    @Query(value = "SELECT c.id as id, c.first_name as first_name, c.last_name as last_name, c.email as email, " +
            "c.password as password FROM users c " +
            "WHERE p.first_name like %?1%", nativeQuery = true)
    ArrayList<Client> findByName(String first_name);

    @Query(value = "SELECT c.id as id, c.first_name as first_name, c.last_name as last_name, c.email as email," +
            " c.password as password FROM users c WHERE c.email = ?1", nativeQuery = true)
    Client findByEmail(String email);

    @Query(value = "SELECT count(1) FROM users c WHERE c.email = ?1", nativeQuery = true)
    int countByEmail(String email);

    @Query(value = "SELECT c.id FROM users c WHERE c.email = ? AND c.password = ?", nativeQuery = true)
    int countByLogin(String email,String pass);

    @Modifying
    @Query(value = "UPDATE users SET first_name = ?1, last_name = ?2, email = ?3, password = ?4 WHERE id = ?5", nativeQuery = true)
    void updateById(String first_name, String last_name, String email, String password, long id);

}
