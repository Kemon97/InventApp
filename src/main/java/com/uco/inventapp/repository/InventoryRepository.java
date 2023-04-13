package com.uco.inventapp.repository;

import com.uco.inventapp.domain.Inventory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long> {
    @Query(value = "SELECT i.id as id, i.client as fk_client, i.product as fk_product, i.state as state " +
            "WHERE i.id like %?1%", nativeQuery = true)
    ArrayList<Inventory> findById(long id);

    @Query(value = "SELECT i.id as id, i.client as fk_client, i.product as fk_product, i.state as state " +
            "WHERE i.fk_client like %?1%", nativeQuery = true)
    ArrayList<Inventory> findByFk_client(String fk_client);

    @Query(value = "SELECT i.id as id, i.client as fk_client, i.product as fk_product, i.state as state " +
            "WHERE i.fk_product like %?1%", nativeQuery = true)
    Inventory findByFk_product(String fk_product);

    @Query(value = "select count(id) from inventories" +
            "WHERE fk_client =?1 and fk_product=?2 and state =1", nativeQuery = true)
    int countByProductoCliente(String fk_client,String fk_product);

    @Modifying
    @Query(value = "UPDATE inventories SET fk_client = ?1, fk_product = ?2, state = ?3 WHERE id = ?4", nativeQuery = true)
    void updateById(String fk_client, String fk_product, boolean state, long id);

}