package com.uco.inventapp.repository;

import com.uco.inventapp.domain.History;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


@Repository
public interface HistoryRepository extends CrudRepository<History, Long> {

    @Query(value = "SELECT c.product as product, c.event_type as event_type, c.date as date, " +
            "WHERE p.product like %?1%", nativeQuery = true)
    ArrayList<History> findByProduct(String product);
    @Query(value = "SELECT c.product as product, c.event_type as event_type, c.date as date, " +
            "WHERE p.product like %?1%", nativeQuery = true)
    History findByDate(String date);

}
