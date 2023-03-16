package com.uco.inventapp.repository;

import com.uco.inventapp.domain.Inventory;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory,Long> {
    @Query(value = "SELECT I.ID AS ID,C.FIRST_NAME AS FIRST_NAME,C.LAST_NAME AS LAST_NAME,P.NAME AS NAME,P.BRAND AS BRAND, I.STATE AS STATE  FROM INVENTORY I " +
            "INNER JOIN USERS C ON C.ID = I.FK_CLIENT" +
            "INNER JOIN PRODUCT P ON P.ID = I.FK_PRODUCT" +
            "WHERE I.ID = 1",nativeQuery = true)
    ArrayList<Inventory> findById(int id);

    @Query(value = "SELECT I.ID AS ID,C.FIRST_NAME AS FIRST_NAME,C.LAST_NAME AS LAST_NAME,P.NAME AS NAME,P.BRAND AS BRAND, I.STATE AS STATE  FROM INVENTORY I " +
            "INNER JOIN USERS C ON C.ID = I.FK_CLIENT" +
            "INNER JOIN PRODUCT P ON P.ID = I.FK_PRODUCT" +
            "WHERE C.ID = 1",nativeQuery = true)
    ArrayList<Inventory> findByFk_client(int id);

    @Query(value = "SELECT I.ID AS ID,C.FIRST_NAME AS FIRST_NAME,C.LAST_NAME AS LAST_NAME,P.NAME AS NAME,P.BRAND AS BRAND, I.STATE AS STATE" +
            "  FROM INVENTORY I " +
            "INNER JOIN USERS C ON C.ID = I.FK_CLIENT" +
            "INNER JOIN PRODUCT P ON P.ID = I.FK_PRODUCT" +
            "WHERE P.ID = 1",nativeQuery = true)
    ArrayList<Inventory> findByFk_product(int id);

    @Query(value = "SELECT COUTN(ID) FROM INVENTORY" +
            "WHERE FK_CLIENT =?1 AND FK_PRODUCT=?2 AND STATE =1", nativeQuery = true)
    int countByProductoCliente(int fk_client,int fk_product);

    @Modifying
    @Query(value = "UPDATE INVENTORY SET FK_CLIENT =?1 , FK_PRODUCT =2?,STATE=?3 WHERE ID=?4")
    void updateById(int fk_cliet,int fk_product,boolean state, int id);

    @Query(value = "UPDATE INVENTORY SET STATE=?1 WHERE ID=?2")
    void updateByIdState(boolean state, int id);


}